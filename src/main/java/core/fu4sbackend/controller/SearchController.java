package core.fu4sbackend.controller;

import core.fu4sbackend.constant.PaginationConstant;
import core.fu4sbackend.dto.LearningMaterialDto;
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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final LearningMaterialService learningMaterialService;
    private final QuestionSetService questionSetService;
    private final PostService postService;

    @Autowired
    public SearchController(LearningMaterialService learningMaterialService, QuestionSetService questionSetService, PostService postService) {
        this.learningMaterialService = learningMaterialService;
        this.questionSetService = questionSetService;
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<String> searchPost(@RequestParam(required = false) String keyword,
                                             @RequestParam(required = false) String subjectCode,
                                             @RequestParam(required = false) Date postTime,
                                             @RequestParam(required = false) Boolean isTest,
                                             @RequestParam(required = false) String username,
                                             @RequestParam(required = false) SearchRequest.SearchOrder order,
                                             @RequestParam Integer pageSize,
                                             @RequestParam(required = false) Integer pageNo) {
        List<PostDto> questionList;
        List<PostDto> materialList;
        SearchRequest sr;
        JSONObject jsonObject = new JSONObject();

        if (Boolean.FALSE.equals(isTest)) {
            questionList = List.of();
        }
        else {
            sr = new SearchRequest(null,keyword,null,null,true,
            SearchRequest.SearchOrder.DATE_DESC, pageSize, (pageNo==null)?0:pageNo-1 );
            questionList = postService.findAllByCriteria(sr);
            jsonObject.put("tests", questionList);
        }

        if (Boolean.TRUE.equals(isTest)) {
            materialList = List.of();
        }
        else {
            sr = new SearchRequest(null,keyword,null,null,false,
                    SearchRequest.SearchOrder.DATE_DESC, pageSize, (pageNo==null)?0:pageNo-1 );
            materialList = postService.findAllByCriteria(sr);
            jsonObject.put("learningMaterials", materialList);
        }

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", jsonObject);
        return ResponseEntity.ok(jsonResponse.toString());

    }
}
