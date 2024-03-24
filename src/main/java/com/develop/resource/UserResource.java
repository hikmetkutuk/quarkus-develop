package com.develop.resource;

import com.develop.dto.AuthRequest;
import com.develop.dto.UserRequest;
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

    /**
     * Register a user by processing the user request.
     *
     * @param  userRequest   the user request object to register
     * @return               the response generated after registering the user
     */
    @POST
    @PermitAll
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserRequest userRequest) {
        return userService.register(userRequest);
    }

    /**
     * A description of the entire Java function.
     *
     * @param  authRequest	description of parameter
     * @return         	description of return value
     */
    @POST
    @PermitAll
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthRequest authRequest) {
        return userService.login(authRequest);
    }
}
