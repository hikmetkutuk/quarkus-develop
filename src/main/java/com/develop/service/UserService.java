package com.develop.service;

import com.develop.dto.AuthRequest;
import com.develop.dto.AuthResponse;
import com.develop.model.Role;
import com.develop.model.User;
import com.develop.security.PBKDF2Encoder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Collections;

@ApplicationScoped
public class UserService {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @ConfigProperty(name = "jwt.duration") public Long duration;
    @ConfigProperty(name = "jwt.verify.issuer") public String issuer;

    public Response login(AuthRequest authRequest) {
        User u = findByUsername(authRequest.username());
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

    public User findByUsername(String username) {

        String userUsername = "user";

        //generated from password encoder
        String userPassword = "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=";

        String adminUsername = "admin";

        //generated from password encoder
        String adminPassword = "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=";

        if (username.equals(userUsername)) {
            User newUser = new User();
            newUser.setUsername(userUsername);
            newUser.setPassword(userPassword);
            newUser.setRoles(Collections.singleton(Role.USER));
            return newUser;
        } else if (username.equals(adminUsername)) {
            User newUser = new User();
            newUser.setUsername(adminUsername);
            newUser.setPassword(adminPassword);
            newUser.setRoles(Collections.singleton(Role.ADMIN));
            return newUser;
        } else {
            return null;
        }
    }
}
