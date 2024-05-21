package core.fu4sbackend.controller;

import core.fu4sbackend.entity.Post;
import core.fu4sbackend.repository.PostRepository;
import core.fu4sbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<Post>> showAllPosts(@RequestParam(required = false) String subject_code,
                                                   @RequestParam(required = false) String title,
                                                   @RequestParam(required = false) int semester) {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }
}
