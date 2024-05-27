package core.fu4sbackend.controller;

import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PostDto>> showAllPosts(@RequestParam(required = false) String title,
                                                      @RequestParam(required = false) String subjectCode,
                                                      @RequestParam(required = false) Date postTime,
                                                      @RequestParam(required = false) Boolean isTest,
                                                      @RequestParam(required = false) String username) {
        SearchRequest sr = new SearchRequest(username, title, subjectCode, postTime, isTest);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<PostDto>> getRecentPost() {
        SearchRequest sr = new SearchRequest(null,null,null,null,null);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }

    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<PostDto>> showAllPostsByUsername(@RequestParam String username,
                                                                @RequestParam Integer pageNum) {
        --pageNum;
        return ResponseEntity.ok(postService.getAllByUsername(username, pageNum));
    }

    @GetMapping("/get")
    public ResponseEntity<PostDto> getPost(@RequestParam(required = false) String id) {
        try {
            if (id==null) throw new Exception();
            PostDto res = postService.getById(Integer.parseInt(id));
            if (res == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
