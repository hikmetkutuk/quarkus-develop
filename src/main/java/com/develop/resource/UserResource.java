package com.develop.resource;

import com.develop.dto.AuthRequest;
import com.develop.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/user")
public class UserResource {
    @Inject
    UserService userService;

    @POST
    @PermitAll
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthRequest authRequest) {
        return userService.login(authRequest);
    }
}
