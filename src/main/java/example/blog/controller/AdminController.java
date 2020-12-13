package example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import example.blog.entity.Post;
import example.blog.service.PostService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	final private PostService postService;
	
	@GetMapping("/admin")
	public String admin(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return"admin/admin";
	}
	
	@PostMapping("/post/add")
	public String addPost(Post post) {
		postService.save(post);
		return "redirect:/";
	}
}
