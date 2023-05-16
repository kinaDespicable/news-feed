package dev.dani.strong.newsfeed.model.dto.request.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(

        @JsonProperty("username")
        @NotBlank(message = "Validation failed: Field 'username' must not be blank")
        String username,

        @JsonProperty("password")
        @NotBlank(message = "Validation failed: Field 'password' must not be blank")
        String password
) {
}
