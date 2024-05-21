package core.fu4sbackend.service;

import core.fu4sbackend.entity.Post;
import core.fu4sbackend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }
    public List<Post> findBySubject(String subject) {
        return postRepository.findBySubject(subject);
    }
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }
}
