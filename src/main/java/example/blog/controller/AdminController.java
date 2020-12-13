package example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import example.blog.entity.Post;

@Controller
public class AdminController {

	
	@GetMapping("/admin")
	public String admin(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return"admin/admin";
	}
}
