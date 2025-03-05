package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import io.smallrye.mutiny.Uni;
import com.example.dto.RepositoryResponse;

@Path("/api/v1/github")
@Produces(MediaType.APPLICATION_JSON)
public class GitHubResource {

    @Inject
    GitHubService gitHubService;

    @GET
    @Path("/{user}")
    public Uni<List<RepositoryResponse>> getRepositories(@PathParam("user") String user) {
        return gitHubService.getNonForkRepositories(user);
    }
}
