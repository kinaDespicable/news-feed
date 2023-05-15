package dev.dani.strong.newsfeed.model.dto.request.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.dani.strong.newsfeed.model.dto.request.CreateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTopicRequest(

        @JsonProperty("topic")
        @NotBlank(message = "Validation failed: Field 'topic' must not be blank")
        @Size(min = 2, max = 64, message = "Validation failed: Field 'topic' must be between 2 and 64 characters long")
        String topic,

        @JsonProperty("description")
        @NotBlank(message = "Validation failed: Field 'description' must not be blank")
        @Size(max = 255, message = "Validation failed: The field must have at maximum length of 255 characters.")
        String description

) implements CreateRequest {
}
