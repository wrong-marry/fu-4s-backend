package core.fu4sbackend.controller;


import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.service.LearningMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<LearningMaterialDto>> getAllLearningMaterialsByUsername(
            @RequestParam String username,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize
    ){
        --pageNum;
        return ResponseEntity.ok(learningMaterialService.getLearningMaterialsByUsername(username, pageNum, pageSize));
    }

    @GetMapping("/getNum")
    public ResponseEntity<Integer> getNumLearningMaterials(@RequestParam String username){
        return ResponseEntity.ok(learningMaterialService.getNumberOfLearningMaterials(username));
    }

    @GetMapping("/")
    public ResponseEntity<LearningMaterialDto> getLearningMaterialById(@RequestParam(value = "id") String id) {
        LearningMaterialDto learningMaterialDto = null;
        try {
            learningMaterialDto = learningMaterialService.getLearningMaterialById(Integer.valueOf(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(learningMaterialDto);
    }

    @PostMapping("/addNew")
    public ResponseEntity<LearningMaterialDto> addNewLearningMaterial(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String subjectCode,
            @RequestBody List<MultipartFile> files,
            @RequestParam String username
    ) throws Exception {
        System.out.println(files.size());
        return ResponseEntity.ok(learningMaterialService.add(title, subjectCode, content, files, username));
    }

    @GetMapping("/getById")
    public ResponseEntity<LearningMaterialDto> getLearningMaterialById(@RequestParam Integer id) {
        return ResponseEntity.ok(learningMaterialService.getById(id));
    }

    @GetMapping("/getFile")
    public ResponseEntity<Resource> getFilesByLearningMaterialId(
            @RequestParam Integer id,
            @RequestParam String filename
    ) throws IOException {
        return learningMaterialService.getFileOfMaterial(id, filename);
    }
}



