package dev.dani.strong.newsfeed.model.dto.request.role;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateRoleRequest(

        @JsonProperty("role")
        String role
) {
}
