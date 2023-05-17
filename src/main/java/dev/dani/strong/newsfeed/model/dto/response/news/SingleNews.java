package dev.dani.strong.newsfeed.model.dto.response.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SingleNews {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("author")
    private String author;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("news_source")
    private String newsSource;

    @JsonProperty("published_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
