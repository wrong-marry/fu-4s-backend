package core.fu4sbackend.controller;

import core.fu4sbackend.service.LearningMaterialService;
import core.fu4sbackend.service.PostService;
import core.fu4sbackend.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/search")
//    public ResponseEntity<String> search(@RequestParam String keyword) {
//        List<LearningMaterialDto> materialList = learningMaterialService.findByKeyword(keyword);
//
//        SearchRequest sr = new SearchRequest(null,keyword,null,null,true);
//        List<PostDto> questionList = postService.findAllByCriteria(sr);
//        JSONObject jsonObject = new JSONObject(List.of(materialList, questionList));
//        jsonObject.put("learningMaterials", materialList);
//        jsonObject.put("tests", questionList);
//
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("data", jsonObject);
//        return ResponseEntity.ok(jsonResponse.toString());
//
//    }
}
