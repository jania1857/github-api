package com.example;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class GitHubExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        // catch exception throw when github API returns 404
        if (exception.getResponse().getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            // return 404 instead of default 500 with message why it fault
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(Map.of(
                            "status", exception.getResponse().getStatus(),
                            "message", "User with provided login not found."
                    ))
                    .build();
        }

        // default case - fetch error status code and message
        return Response
                .status(exception.getResponse().getStatus())
                .entity(Map.of(
                        "status", exception.getResponse().getStatus(),
                        "message", exception.getMessage()
                ))
                .build();
    }
}
