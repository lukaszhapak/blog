package example.blog.controller;

import example.blog.entity.User;
import example.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, Model model) {

        boolean error = false;
        HashMap<String, String> errors = new HashMap<>();

        if (userService.findByUserName(user.getUserName()).isPresent()) {
            errors.put("userName", "Użytkownik o takim loginie już istnieje");
            error = true;
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            errors.put("email", "Użytkownik o takim adresie email już istnieje");
            error = true;
        }

        if (bindingResult.hasErrors()) {
            error = true;
        }

        if (error) {
            model.addAttribute("uniqueErrors", errors);
            return "user/register";
        }
        user.setRole(example.blog.enums.Role.ROLE_USER);
        userService.save(user);
        return "redirect:/";
    }

}
