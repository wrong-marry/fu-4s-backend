package core.fu4sbackend.controller;

import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // ----
@RequestMapping("/api/v1/questionSet") //---
@CrossOrigin
public class QuestionSetController {

    private final QuestionSetService questionSetService;
    @Autowired
    public QuestionSetController(QuestionSetService questionSetService){
        this.questionSetService = questionSetService ;
    }


    @GetMapping("/getAll")
    public List<QuestionSetDto> getAllQuestionSets(){
        List<QuestionSetDto> questionSets = questionSetService.getAllQuestionSets();
        return questionSets;
    }

    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<QuestionSetDto>> getAllQuestionSetsByUsername(String username){
        return ResponseEntity.ok(questionSetService.getQuestionSetsByUsername(username));
    }

    @PutMapping("/editQuestionSet")
    public ResponseEntity<QuestionSetDto> editQuestionSet(@RequestBody QuestionSetDto questionSetDto,
                                                   @RequestParam String username) throws Exception {
        questionSetService.editQuestionSet(questionSetDto, username);
        return ResponseEntity.ok(questionSetDto);
    }

    @DeleteMapping("/removeQuestionSet")
    public ResponseEntity<String> removeQuestionSet(@RequestParam Integer id,
                                                        @RequestParam String username) throws Exception {
        questionSetService.removeQuestionSet(id, username);
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/")
    public ResponseEntity<QuestionSetDto> getQuestionSetById(@RequestParam(value = "id") String id) {
        QuestionSetDto questionSetDto = null;
        try {
            questionSetDto = questionSetService.getQuestionSetById(Integer.valueOf(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(questionSetDto);
    }
}
