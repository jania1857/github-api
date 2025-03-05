package com.example;

import com.example.dto.GitHubBranch;
import com.example.dto.GitHubRepo;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
public interface GitHubClient {

    @GET
    @Path("/users/{user}/repos")
    Uni<List<GitHubRepo>> getRepositories(
            @PathParam("user") String user
    );

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    Uni<List<GitHubBranch>> getBranches(
            @PathParam("owner") String owner,
            @PathParam("repo") String repo
    );
}
