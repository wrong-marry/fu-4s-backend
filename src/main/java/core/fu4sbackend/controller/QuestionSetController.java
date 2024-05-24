package core.fu4sbackend.controller;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
