package core.fu4sbackend.controller;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.service.PostService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/staff")
@Secured({"STAFF","ADMIN"})
public class StaffController {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
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
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.ALREADY_REPORTED);
        }
    }
    @PutMapping("/restoredPost")
    public ResponseEntity<String> restoredAPost(@RequestParam("id") Integer Postid) {
        JSONObject jsonObject = new JSONObject();
        try {
            postService.restoredPost(Postid);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.ALREADY_REPORTED);
        }
    }

    @PutMapping("/hidenPost")
    public ResponseEntity<String> hidenAPost(@RequestParam("id") Integer Postid) {
        JSONObject jsonObject = new JSONObject();
        try {
            postService.hidenPost(Postid);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.ALREADY_REPORTED);
        }
    }
    @PutMapping("/deletedPost")
    public ResponseEntity<String> deletedAPost(@RequestParam("id") Integer Postid) {
        JSONObject jsonObject = new JSONObject();
        try {
            postService.deletedPost(Postid);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.ALREADY_REPORTED);
        }
    }
    @GetMapping("/compareNumberPosts")
    public ResponseEntity<Integer> comparePostsThisMonthLastMonth() {
        JSONObject jsonObject = new JSONObject();
        try {
            int numOfPostsNow = postService.getNumberOfAllPosts();
            int numOfPostsThisMonth = postService.getNumberOfPostsThisMonth();

            double percentChange = postService.calculatePercentageChangePost(numOfPostsNow - numOfPostsThisMonth, numOfPostsThisMonth);
            int percentChangeInt = (int) percentChange;

            return new ResponseEntity<>(percentChangeInt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/numberMaterialsNew")
    public ResponseEntity<Integer> NumberMaterialsNew() {
        try {
            int numOfTestsNew = postService.getNumberOfMaterialsThisMonth();
            return new ResponseEntity<>(numOfTestsNew, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/numberTestsNew")
    public ResponseEntity<Integer> NumberTestsNew() {
        try {
            int numOfTestsNew = postService.getNumberOfTestsThisMonth();
            return new ResponseEntity<>(numOfTestsNew, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getStatisticAboutPostEachSubject")
    public ResponseEntity<Map<String, Map<YearMonth, Integer>>> getStatisticAboutPostEachSubject() {
        try {
            Map<String, Map<YearMonth, Integer>> statistics = postService.getNumberOfPostsPerSubjectLast12Months();
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching statistics about posts for each subject: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchAndFilterPosts")
    public ResponseEntity<List<PostDto>> searchAndFilterPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String subject
           ) {
        List<PostDto> posts = postService.searchAndFilterPosts(keyword, subject);
        return ResponseEntity.ok(posts);
    }

}

