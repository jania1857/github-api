package com.example.dto;

import java.util.List;

public class RepositoryResponse {
    public String repositoryName;
    public String ownerLogin;
    public List<GitHubBranch> branches;

    public RepositoryResponse(
            String repositoryName,
            String ownerLogin,
            List<GitHubBranch> branches
    ) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }
}
