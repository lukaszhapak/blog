package example.blog.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 120, message = "Komentarz nie może być dłuższy niż 120 znaków")
    @Size(min = 3, message = "Komentarz musi się składać z conajmniej 3 znaków")
    @Column(columnDefinition = "TEXT")
    private String text;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private User author;
    @ManyToOne
    private Post post;
}
