package com.example;

import com.example.dto.GitHubRepo;
import com.example.dto.RepositoryResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class GitHubService {

    @RestClient
    GitHubClient gitHubClient;

    public Uni<List<RepositoryResponse>> getNonForkRepositories(String user) {
        return gitHubClient.getRepositories(user)
                .onItem().transformToUni(repos -> {
                    if (repos == null || repos.isEmpty()) {
                        throw new WebApplicationException("User not found", 404);
                    }
                    List<GitHubRepo> nonForkRepos = repos.stream()
                            .filter(repo -> !repo.fork)
                            .toList();

                    List<Uni<RepositoryResponse>> responseUnis = nonForkRepos.stream()
                            .map(repo -> gitHubClient.getBranches(
                                    repo.owner.login, repo.name
                            ).onItem().transform(branches -> new RepositoryResponse(
                                    repo.name,
                                    repo.owner.login,
                                    branches
                            ))).toList();

                    return Uni.combine().all().unis(responseUnis)
                            .with(responses -> responses.stream()
                                    .map(response -> (RepositoryResponse) response)
                                    .toList());
                });
    }
}
