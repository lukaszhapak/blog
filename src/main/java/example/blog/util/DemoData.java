package example.blog.util;

import example.blog.entity.User;
import example.blog.enums.Role;
import example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DemoData implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {

        if (userService.count() == 0) {

            User user = new User();
            user.setUserName("admin");
            user.setPassword("123");
            user.setRole(Role.ROLE_ADMIN);
            userService.save(user);
        }
    }
}