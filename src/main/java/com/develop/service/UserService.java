package com.develop.service;

import com.develop.dto.AuthRequest;
import com.develop.dto.AuthResponse;
import com.develop.dto.UserRequest;
import com.develop.model.User;
import com.develop.repository.UserRepository;
import com.develop.security.PBKDF2Encoder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Transactional
@ApplicationScoped
public class UserService {

    @Inject
    PBKDF2Encoder passwordEncoder;
    @Inject
    UserRepository userRepository;

    @ConfigProperty(name = "jwt.duration")
    public Long duration;
    @ConfigProperty(name = "jwt.verify.issuer")
    public String issuer;

    public Response login(AuthRequest authRequest) {
        User u = userRepository.findByUsername(authRequest.username());
        if (u != null && u.getPassword().equals(passwordEncoder.encode(authRequest.password()))) {
            try {
                return Response.ok(new AuthResponse(TokenService.generateToken(u.getUsername(), u.getRoles(), duration, issuer))).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Register a new user with the provided information.
     *
     * @param  userRequest   the user request object containing user details
     * @return               the response indicating the success or failure of registration
     */
    public Response register(UserRequest userRequest) {
        User newUser = new User();
        newUser.setUsername(userRequest.username());
        newUser.setPassword(passwordEncoder.encode(userRequest.password()));
        newUser.setRoles(userRequest.roles());

        try {
            userRepository.persist(newUser);
            return Response.ok(new AuthResponse(TokenService.generateToken(newUser.getUsername(), newUser.getRoles(), duration, issuer))).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while registering user: " + e.getMessage())
                    .build();
        }
    }
}
