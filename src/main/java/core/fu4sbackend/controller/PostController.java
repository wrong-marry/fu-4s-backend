package core.fu4sbackend.controller;

import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.service.PostService;
import org.json.JSONObject;
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
    public ResponseEntity<String> showAllPosts(@RequestParam(required = false) String title,
                                               @RequestParam(required = false) String subjectCode,
                                               @RequestParam(required = false) Integer semester,
                                               @RequestParam(required = false) Date postTime,
                                               @RequestParam(required = false) Boolean isTest,
                                               @RequestParam(required = false) String username,
                                               @RequestParam(required = false) SearchRequest.SearchOrder order,
                                               @RequestParam Integer pageSize,
                                               @RequestParam(required = false) Integer page) {
        SearchRequest sr = new SearchRequest(username, title, subjectCode, postTime, isTest, order, pageSize, page, semester);
        List<PostDto> list = postService.findAllByCriteria(sr);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("posts", list);
        jsonObject.put("total", postService.countAllByCriteria(sr));
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }


    @GetMapping("/recent")
    public ResponseEntity<List<PostDto>> getRecentPost(@RequestParam(required = false) Integer offset) {
        SearchRequest sr = new SearchRequest(null, null, null, null, null, SearchRequest.SearchOrder.DATE_DESC, PaginationConstant.RECENT_POST_LOAD_SIZE, offset == null ? 1 : (PaginationConstant.RECENT_POST_LOAD_SIZE / offset), null);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }

    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<PostDto>> showAllPostsByUsername(@RequestParam String username,
                                                                @RequestParam Integer pageNum,
                                                                @RequestParam Integer pageSize
    ) {
        --pageNum;
        return ResponseEntity.ok(postService.getAllByUsername(username, pageNum, pageSize));
    }

    @GetMapping("/getNum")
    public ResponseEntity<Integer> getNumPosts(@RequestParam String username) {
        return ResponseEntity.ok(postService.getNumberOfPosts(username));
    }

    @GetMapping("/isValidUser")
    public ResponseEntity<Boolean> isValidUser(@RequestParam String username, @RequestParam Integer id) {
        return ResponseEntity.ok(postService.isValidUser(username, id));
    }

    @GetMapping("/get")
    public ResponseEntity<PostDto> getPost(@RequestParam(required = false) String id) {
        try {
            if (id == null) throw new Exception();
            PostDto res = postService.getById(Integer.parseInt(id));
            if (res == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUsernameById")
    public String getUsernameById(@RequestParam String id) {
        try {
            if (id == null) throw new Exception();
            return  postService.UsernameById(Integer.parseInt(id));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getPostByUserName")
    public ResponseEntity<List<PostDto>> getPostByUserName(
            @RequestParam String username,
            @RequestParam(required = false) Integer offset) {
        SearchRequest sr = new SearchRequest(username, null, null, null, null, SearchRequest.SearchOrder.DATE_DESC, PaginationConstant.RECENT_POST_LOAD_SIZE, offset == null ? 1 : (PaginationConstant.RECENT_POST_LOAD_SIZE / offset), null);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }

    @GetMapping("/getPostBySubjectCode")
    public ResponseEntity<List<PostDto>> getPostBySubjectCode(
            @RequestParam String subjectCode,
            @RequestParam(required = false) Integer offset) {
        SearchRequest sr = new SearchRequest(null, null, subjectCode, null, null, SearchRequest.SearchOrder.DATE_DESC, PaginationConstant.RECENT_POST_LOAD_SIZE, offset == null ? 1 : (PaginationConstant.RECENT_POST_LOAD_SIZE / offset), null);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }
}
