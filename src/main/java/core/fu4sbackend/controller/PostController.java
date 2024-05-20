package core.fu4sbackend.controller;

import core.fu4sbackend.entity.Post;
import core.fu4sbackend.repository.PostRepository;
import core.fu4sbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    public ResponseEntity<List<Post>> showAllPosts() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<List<Post>> showAllPostsWithSubject(String subject) {
        return new ResponseEntity<>(postService.findBySubject(subject), HttpStatus.OK);
    }
    public ResponseEntity<List<Post>> showAllPostsBySubject(String subject) {
        return new ResponseEntity<>(postService.findBySubject(subject), HttpStatus.OK);
    }
}
