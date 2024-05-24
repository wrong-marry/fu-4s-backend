package core.fu4sbackend.controller;

import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.service.LearningMaterialService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<LearningMaterialDto> getAllLearningMaterials(){
        return learningMaterialService.getAllLearningMaterials();
    }

    @GetMapping
    public ResponseEntity<LearningMaterialDto> getLearningMaterialById(@RequestParam(value = "id") String id) {
        LearningMaterialDto learningMaterialDto = learningMaterialService.getLearningMaterialById(Integer.valueOf(id));
        return ResponseEntity.ok(learningMaterialDto);
    }

}


