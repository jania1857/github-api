package com.example.dto;

public class GitHubBranchResponse {
    public String name;
    public String lastCommitSha;

    public GitHubBranchResponse(GitHubBranch ghBranch) {
        this.name = ghBranch.name;
        this.lastCommitSha = ghBranch.commit.sha;
    }
}
