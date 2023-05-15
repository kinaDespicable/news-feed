package dev.dani.strong.newsfeed.model.dto.request.newsSource;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public record UpdateNewsSourceRequest(

        @JsonProperty("name")
        @Size(min = 2, max = 64, message = "Validation failed: Field 'name' must be between 2 and 64 characters long")
        String name,

        @JsonProperty("source_name")
        @Size(min = 2, max = 255, message = "Validation failed: Field 'source_name' must be between 2 and 255 characters long")
        String sourceName,

        @JsonProperty("url")
        @Size(max = 255, message = "Validation failed: Field 'url' must have at maximum length of 255 characters.")
        String url,

        @JsonProperty("description")
        String description
) {
}
