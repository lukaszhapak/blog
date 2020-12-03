package example.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import example.blog.entity.Post;
import example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
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

