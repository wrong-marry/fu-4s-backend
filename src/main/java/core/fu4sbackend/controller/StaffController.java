package core.fu4sbackend.controller;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.service.PostService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@Secured({"STAFF","ADMIN"})
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
    @GetMapping("/getNumAllPost")
    public ResponseEntity<Integer> getNumberOfAllPosts(){
        return ResponseEntity.ok(postService.getNumberOfAllPosts());
    }

    @GetMapping("/getAllPosts")
    public List<PostDto> getAllPosts(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize){
        --pageNum;
        List<PostDto> postDtoList = postService.getAllPosts(pageNum, pageSize);
        return postDtoList;
    }

    @PutMapping("/approvedPost")
    public ResponseEntity<String> approveAPost(@RequestParam("id") Integer Postid) {
        JSONObject jsonObject = new JSONObject();
        try {
            postService.approvePost(Postid);
            jsonObject.put("message", "Approved successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deniedPost")
    public ResponseEntity<String> deniedAPost(@RequestParam("id") Integer Postid) {
        JSONObject jsonObject = new JSONObject();
        try {
            postService.deniedPost(Postid);
            jsonObject.put("message", "Approved successfully!");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            jsonObject.put("message", "Internal server error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.ALREADY_REPORTED);
        }
    }

}

