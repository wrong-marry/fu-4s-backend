package core.fu4sbackend.controller;

import core.fu4sbackend.dto.QuestionPriorityDTO;
import core.fu4sbackend.service.QuestionPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question-priority")
public class QuestionPriorityController {
    private final QuestionPriorityService questionPriorityService;

    @Autowired
    public QuestionPriorityController(QuestionPriorityService questionPriorityService) {
        this.questionPriorityService = questionPriorityService;
    }

    @PostMapping("/initiate")
    public String initiate(@RequestParam String username, @RequestParam int questionSetId) {
        return questionPriorityService.initiateQuestionPrioritiesByUsernameAndQuestionSetId(username,questionSetId);
    }

    @GetMapping
    public List<QuestionPriorityDTO> findAllByUsernameAndQuestionSetId(@RequestParam String username, @RequestParam int questionSetId){
        return questionPriorityService.getAllQuestionPriorityByUsernameAndQuestionSetId(username,questionSetId);
    }
}
