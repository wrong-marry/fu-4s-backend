package core.fu4sbackend.controller;

import core.fu4sbackend.service.QuestionPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question-priority")
public class QuestionPriorityController {
    private final QuestionPriorityService questionPriorityService;

    @Autowired
    public QuestionPriorityController(QuestionPriorityService questionPriorityService) {
        this.questionPriorityService = questionPriorityService;
    }


}
