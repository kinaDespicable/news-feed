package dev.dani.strong.newsfeed.model.dto.request.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public record UpdateTopicRequest(

        @JsonProperty("topic")
        @Size(min = 2, max = 64, message = "Validation failed: Field 'topic' must be between 2 and 64 characters long")
        String topic,

        @JsonProperty("description")
        @Size(max = 255, message = "The field must have at maximum length of 255 characters.")
        String description
) {
}