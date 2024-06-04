package core.fu4sbackend.controller;

import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.service.PostService;
import org.json.JSONObject;
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
    public ResponseEntity<List<PostDto>> getRecentPost(@RequestParam(required = false) Integer page) {
        SearchRequest sr = new SearchRequest(null, null, null, null, null, SearchRequest.SearchOrder.DATE_DESC, PaginationConstant.RECENT_POST_LOAD_SIZE, page, null);
        return new ResponseEntity<>(postService.findAllByCriteria(sr), HttpStatus.OK);
    }

    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<PostDto>> showAllPostsByUsername(@RequestParam String username) {
        return ResponseEntity.ok(postService.getAllByUsername(username));
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
}
