package core.fu4sbackend.controller;

import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<List<Post>> showAllPosts(@RequestParam(required = false) String title,
                                                   @RequestParam(required = false) String subject_code,
                                                   @RequestParam(required = false) Date post_time,
                                                   @RequestParam(required = false) boolean is_test) {
        SearchRequest sr = new SearchRequest(title, subject_code, post_time, is_test);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }
}
