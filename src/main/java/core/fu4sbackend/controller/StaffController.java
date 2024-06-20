package core.fu4sbackend.controller;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@Secured("STAFF")
public class StaffController {
    private final PostService postService;

    public StaffController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/getAllByPostStatus")
    public ResponseEntity<List<PostDto>> showAllPostsByPostStatus(@RequestParam PostStatus status,
                                                                  @RequestParam Integer pageNum,
                                                                  @RequestParam Integer pageSize
    ) {
        --pageNum;
        return ResponseEntity.ok(postService.getAllByPostStatus(status , pageNum , pageSize));
    }
    @GetMapping("/getNumEachStatus")
    public ResponseEntity<Integer> getNumPostsEachStatus(@RequestParam PostStatus status){
        return ResponseEntity.ok(postService.getNumberOfPostsEachStatus(status));
    }
}
