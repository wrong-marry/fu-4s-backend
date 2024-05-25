package core.fu4sbackend.controller;

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
    public ResponseEntity<String> searchPost(@RequestParam(required = false) String keyword) {
        List<LearningMaterialDto> materialList = learningMaterialService.findByKeyword(keyword);

        SearchRequest sr = new SearchRequest(null,keyword,null,null,null);
        List<PostDto> questionList = postService.findAllByCriteria(sr);
        JSONObject jsonObject = new JSONObject(List.of(materialList, questionList));
        jsonObject.put("learningMaterials", materialList);
        jsonObject.put("tests", questionList);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", jsonObject);
        return ResponseEntity.ok(jsonResponse.toString());

    }
}
