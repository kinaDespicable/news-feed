package dev.dani.strong.newsfeed.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class News implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456789L;


    @Id
    @JsonProperty("news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("title")
    @Column(name = "title", nullable = false)
    private String title;

    @JsonProperty("content")
    @Column(name = "content", nullable = false)
    private String content;

    @JsonProperty("created_at")
    @Column(name = "published_at", updatable = false)
    private LocalDateTime publishedAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_source_id", updatable = false)
    private NewsSource newsSource;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News news)) return false;
        return Objects.equals(id, news.id) &&
                Objects.equals(title, news.title) &&
                Objects.equals(content, news.content) &&
                Objects.equals(publishedAt, news.publishedAt) &&
                Objects.equals(updatedAt, news.updatedAt) &&
                Objects.equals(topic, news.topic) &&
                Objects.equals(author, news.author) &&
                Objects.equals(newsSource, news.newsSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, publishedAt, updatedAt, topic, author, newsSource);
    }
}
