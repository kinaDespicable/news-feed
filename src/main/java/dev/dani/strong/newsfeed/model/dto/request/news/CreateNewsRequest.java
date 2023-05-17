package dev.dani.strong.newsfeed.model.dto.request.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.dani.strong.newsfeed.model.dto.request.CreateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateNewsRequest(

        @JsonProperty("title")
        @NotBlank(message = "Validation failed: Field 'title' must not be blank")
        @Size(max = 150, message = "Validation failed: Field 'title' must have at maximum length of 150 characters.")
        String title,

        @JsonProperty("content")
        @NotBlank(message = "Validation failed: Field 'content' must not be blank")
        String content,

        @JsonProperty("topic")
        @NotBlank(message = "Validation failed: Field 'topic' must not be blank")
        String topic,

        @JsonProperty("news_source")
        @NotBlank(message = "Validation failed: Field 'news_source' must not be blank")
        String newsSource

) implements CreateRequest {
}
