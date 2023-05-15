package dev.dani.strong.newsfeed.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news_source")
public class NewsSource implements Serializable {

    @Serial
    private static final long serialVersionUID = 987654321L;

    @Id
    @JsonProperty("news_source_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonProperty("source_name")
    @Column(name = "source_name", nullable = false)
    private String sourceName;

    @JsonProperty("url")
    @Column(name = "url", nullable = false)
    private String url;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    @JsonProperty("registered_at")
    @Column(name = "registered_at")
    private LocalDate registeredAt;

    @OneToMany(mappedBy = "newsSource")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<News> news = new LinkedHashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsSource that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sourceName, that.sourceName) &&
                Objects.equals(url, that.url) &&
                Objects.equals(description, that.description) &&
                Objects.equals(registeredAt, that.registeredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sourceName, url, description, registeredAt);
    }
}
