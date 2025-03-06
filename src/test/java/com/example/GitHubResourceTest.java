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
                    .body("$", not(empty()))
                    .body("repositoryName", everyItem(not(emptyOrNullString())))
                    .body("ownerLogin", everyItem(not(emptyOrNullString())))
                    .body("branches", everyItem(not(empty())))
                    .body("branches.name", everyItem(not(emptyOrNullString())))
                    .body("branches.lastCommitSha", everyItem(everyItem(matchesPattern("^[a-f0-9]{40}$"))));
    }
}
