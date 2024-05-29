package core.fu4sbackend.controller;

import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.service.LearningMaterialService;
import core.fu4sbackend.service.PostService;
import core.fu4sbackend.service.QuestionSetService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final PostService postService;

    @Autowired
    public SearchController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<String> searchPost(@RequestParam(required = false) String keyword,
                                             @RequestParam(required = false) String subjectCode,
                                             @RequestParam(required = false) String postTime,
                                             @RequestParam(required = false) String isTest,
                                             @RequestParam(required = false) String username,
                                             @RequestParam(required = false) SearchRequest.SearchOrder order,
                                             @RequestParam Integer pageSize,
                                             @RequestParam(required = false) Integer page) {
        Boolean test = null;
        try {
            switch (isTest.trim().toLowerCase()) {
                case "true":
                    test = true;
                    break;
                case "false":
                    test = false;
                    break;
                default:
                    break;
            }
        } catch (Exception _) {
        }
        Date time = null;
        try {
            time = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss SS:SS'Z'").parse(postTime);
        } catch (Exception _) {
        }

        List<PostDto> questionList;
        List<PostDto> materialList;
        SearchRequest sr;
        JSONObject jsonObject = new JSONObject();
        Integer totalTest = 0, totalMaterial = 0;

        if (Boolean.FALSE.equals(test)) {
            questionList = List.of();
        } else {
            sr = new SearchRequest(username, keyword, subjectCode, time, true,
                    SearchRequest.SearchOrder.DATE_DESC, pageSize, (page == null) ? 0 : page - 1);
            if (order != null) sr.setOrder(order);
            totalTest = postService.countAllByCriteria(sr);
            questionList = postService.findAllByCriteria(sr);
            jsonObject.put("tests", questionList);
        }

        if (Boolean.TRUE.equals(test)) {
            materialList = List.of();
        } else {
            sr = new SearchRequest(username, keyword, subjectCode, time, false,
                    SearchRequest.SearchOrder.DATE_DESC, pageSize, (page == null) ? 0 : page - 1);
            if (order != null) sr.setOrder(order);
            materialList = postService.findAllByCriteria(sr);
            totalMaterial = postService.countAllByCriteria(sr);
            jsonObject.put("learningMaterials", materialList);
        }
        jsonObject.put("totalTest", totalTest);
        jsonObject.put("totalMaterial", totalMaterial);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", jsonObject);
        return ResponseEntity.ok(jsonResponse.toString());

    }
}
