package dev.dani.strong.newsfeed.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(

        @JsonProperty("first_name")
        @NotBlank(message = "Validation failed: Field 'first_name' must not be blank")
        @Size(min = 2, max = 32, message = "Validation failed: Field 'first_name' must be between 2 and 32 characters long")
        String firstName,

        @JsonProperty("last_name")
        @NotBlank(message = "Validation failed: Field 'last_name' must not be blank")
        @Size(min = 2, max = 32, message = "Validation failed: Field 'last_name' must be between 2 and 32 characters long")
        String lastName,

        @JsonProperty("username")
        @NotBlank(message = "Validation failed: Field 'username' must not be blank")
        @Size(min = 2, max = 64, message = "Validation failed: Field 'username' must be between 2 and 64 characters long")
        String username,

        @JsonProperty("password")
        @NotBlank(message = "Validation failed: Field 'password' must not be blank")
        @Size(min = 8, max = 255, message = "Validation failed: Field 'password' must be between 8 and 255 characters long")
        String password,

        @JsonProperty("password_confirmation")
        @NotBlank(message = "Validation failed: Field 'password_confirmation' must not be blank")
        @Size(min = 8, max = 255, message = "Validation failed: Field 'password_confirmation' must be between 8 and 255 characters long")
        String passwordConfirmation

) implements CreateRequest {
}
