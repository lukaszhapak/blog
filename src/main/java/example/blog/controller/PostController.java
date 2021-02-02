package example.blog.controller;

import example.blog.entity.Comment;
import example.blog.entity.MyUserDetails;
import example.blog.entity.Post;
import example.blog.service.CommentService;
import example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

	final private PostService postService;
	final private CommentService commentService;

	@GetMapping("/post/{id}")
	public String post(Model model, @PathVariable int id) {
		model.addAttribute("post", postService.findById(id));
		model.addAttribute("comment", new Comment());
		return "/post/post";
	}

	@PostMapping("/post/{id}/comment/add")
	public String register(Comment comment, Model model, @PathVariable int id, @AuthenticationPrincipal MyUserDetails currentUser) {
		Post post = new Post();
		post.setId((long) id);
		comment.setPost(post);
		comment.setAuthor(currentUser.getUser());
		commentService.save(comment);
		return "redirect:/post/" + id;
	}

}
