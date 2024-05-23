package core.fu4sbackend.controller;

import core.fu4sbackend.dto.SearchRequest;
import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.entity.Post;
import core.fu4sbackend.service.LearningMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/learningMaterial")
public class LearningMaterialController {

    @Autowired
    private final LearningMaterialService learningMaterialService;

    public LearningMaterialController(LearningMaterialService learningMaterialService) {
        this.learningMaterialService = learningMaterialService;
    }
    @GetMapping("/getAll")
    public List<LearningMaterial> getAllLearningMaterials(){
        return learningMaterialService.getAllLearningMaterials();
    }

//    @GetMapping("/getAllLearningMaterials")
//    public ResponseEntity<String> getAllLearningMaterials(){
//        return ResponseEntity.ok(learningMaterialService.getAllLearningMaterials().toString()) ;
//    }
}


