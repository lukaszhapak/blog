package example.blog.controller;

import example.blog.entity.Post;
import example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AdminController {

    final private PostService postService;

    @GetMapping("/admin")
    public String admin(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "admin/admin";
    }

    @PostMapping("/admin/post/add")
    public String addPost(@Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/admin";
        }
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/admin/post/{id}/delete")
    public String deletePost(@PathVariable int id) {
        postService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/admin/post/{id}/update")
    public String editPost(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "admin/update";
    }

    @PostMapping("/admin/post/{id}/update")
    public String updatePost(@PathVariable int id, Post post) {
        postService.save(post);
        return "redirect:/";
    }
}
