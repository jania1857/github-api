package com.example.dto;

import java.util.List;

public class RepositoryResponse {
    public String repositoryName;
    public String ownerLogin;
    public List<GitHubBranchResponse> branches;

    public RepositoryResponse(
            String repositoryName,
            String ownerLogin,
            List<GitHubBranch> branches
    ) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches.stream().map(GitHubBranchResponse::new).toList();
    }
}
