package dev.dani.strong.newsfeed.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topic")
public class Topic implements Serializable {

    @Serial
    private static final long serialVersionUID = 555555555L;

    @Id
    @Column(name = "id")
    @JsonProperty("topic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("topic")
    @Column(name = "topic", nullable = false)
    private String topic;

    @JsonProperty("description")
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "topic")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<News> news = new LinkedHashSet<>();

    @Builder
    public Topic(String topic, String description) {
        this.topic = topic;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic topic1)) return false;
        return Objects.equals(id, topic1.id) &&
                Objects.equals(topic, topic1.topic) &&
                Objects.equals(description, topic1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, description);
    }
}
