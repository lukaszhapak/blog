package example.blog.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private User author;
    @ManyToOne
    private Post post;
}
