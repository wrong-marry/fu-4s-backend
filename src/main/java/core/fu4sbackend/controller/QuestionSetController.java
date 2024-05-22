package core.fu4sbackend.controller;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
