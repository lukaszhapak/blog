package example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCorntoller {

	
	@GetMapping("/admin")
	public String admin() {
		return"admin";
	}
}
