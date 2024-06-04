package core.fu4sbackend.controller;

import core.fu4sbackend.dto.AnswerDto;
import core.fu4sbackend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {
    private final AnswerService answerService;
    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
    @GetMapping("/get-by-question-id")
    public List<AnswerDto> getByQuestionId(@RequestParam int questionId){
        return answerService.getByQuestionId(questionId);
    }
}
