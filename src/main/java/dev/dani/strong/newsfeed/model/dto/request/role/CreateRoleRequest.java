package dev.dani.strong.newsfeed.model.dto.request.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.dani.strong.newsfeed.model.dto.request.CreateRequest;
import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequest(

        @JsonProperty("role")
        @NotBlank(message = "Validation failed: Field 'role' must not be blank")
        String role

) implements CreateRequest {
}

