package example.blog.controller;

import example.blog.entity.Comment;
import example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PostController {

    final private PostService postService;

    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable int id) {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("comment", new Comment());
        return "/post/post";
    }

}
