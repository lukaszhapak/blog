package example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import example.blog.entity.Post;
import example.blog.service.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostService postService;
	
	@GetMapping("/")
	String index(Model model) {
		model.addAttribute("posts", postService.findAll());
		return "index";
	}

}
