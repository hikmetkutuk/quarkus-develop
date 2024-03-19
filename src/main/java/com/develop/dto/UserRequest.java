package com.develop.dto;

import com.develop.model.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UserRequest(
        @NotBlank String username,
        @NotBlank String password,
        Set<Role> roles
) {
}
