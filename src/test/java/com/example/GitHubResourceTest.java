package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GitHubResourceTest {

    @Test
    void shouldReturnNonForkRepositoriesWithBranchesAndCommits() {
        given()
                .when()
                    .get("/api/v1/github/octocat")
                .then()
                    .statusCode(200)
                    .contentType("application/json")
                    .body("$", not(empty())) // check if body is present
                    .body("repositoryName", everyItem(not(emptyOrNullString()))) // check if repository name is present
                    .body("ownerLogin", everyItem(not(emptyOrNullString()))) // check if owner login is present
                    .body("branches", everyItem(not(empty()))) // check if list of branches is not empty
                    .body("branches.name", everyItem(not(emptyOrNullString()))) // check if branch name is present
                    .body("branches.lastCommitSha", everyItem(everyItem(matchesPattern("^[a-f0-9]{40}$")))); // check if sha has a valid format
    }
}
