package example.blog.service;

import example.blog.entity.Post;
import example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public Post findById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }
}

