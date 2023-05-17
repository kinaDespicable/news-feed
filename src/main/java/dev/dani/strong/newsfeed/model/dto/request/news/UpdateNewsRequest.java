package dev.dani.strong.newsfeed.model.dto.request.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public record UpdateNewsRequest(

        @JsonProperty("title")
        @Size(max = 150, message = "Validation failed: Field 'title' must have at maximum length of 150 characters.")
        String title,

        @JsonProperty("content")
        String content


) {
}
