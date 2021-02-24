package example.blog.entity;

import example.blog.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 24, message = "Nazwa użytkownika nie może być dłuższa niż 24 znaki")
    @Size(min = 3, message = "Nazwa użytkownika musi się zkładać z conajmniej 3 znaków")
    private String userName;
    @Size(min = 8, message = "Hasło musi się składać z conajmniej 8 znaków")
    private String password;
    @Pattern(regexp = "^(.+)@(.+)$", message = "Podaj prawidłowy adres email")
    private String email;
    private Role role;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

}
