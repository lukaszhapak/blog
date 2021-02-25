package example.blog.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 64, message = "Tytuł posta nie może być dłuższy niż 64 znaki")
    @Size(min = 3, message = "Tytuł musi się składać z conajmniej 3 znaków")
    private String title;
    @Size(max = 1000, message = "Tekst nie może przekraczać 1000 znaków")
    @Size(min = 5, message = "Tekst musi zawierać przynajmniej 5 znaków")
    @Column(columnDefinition = "TEXT")
    private String text;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

}
