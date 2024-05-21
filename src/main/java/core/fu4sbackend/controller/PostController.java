package core.fu4sbackend.controller;

import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.repository.PostRepository;
import core.fu4sbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<Post>> showAllPosts(@RequestParam(required = false) String title,
                                                   @RequestParam(required = false) String subject_code,
                                                   @RequestParam(required = false) Date post_time,
                                                   @RequestParam(required = false) boolean is_test) {
        SearchRequest sr = new SearchRequest(title, subject_code, post_time, is_test);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }
}
