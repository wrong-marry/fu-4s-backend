package core.fu4sbackend.controller;

import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.service.LearningMaterialService;
import core.fu4sbackend.service.PostService;
import core.fu4sbackend.service.QuestionSetService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController("/api/v1")
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

    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam String keyword) {
        List<LearningMaterialDto> materialList = learningMaterialService.findByKeyword(keyword);

        SearchRequest sr = new SearchRequest(null,keyword,null,null,true);
        List<PostDto> questionList = postService.findAllByCriteria(sr);
        JSONObject jsonObject = new JSONObject(List.of(materialList, questionList));
        jsonObject.put("learningMaterials", materialList);
        jsonObject.put("tests", questionList);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("data", jsonObject);
        return ResponseEntity.ok(jsonResponse.toString());

    }
}
