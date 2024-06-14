package core.fu4sbackend.controller;

import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.repository.QuestionRepository;
import core.fu4sbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/get-by-question-set-id")
    public List<QuestionDto> getByQuestionSetId(@RequestParam int questionSetId) {
        return questionService.getByQuestionSetId(questionSetId);
    }

    @GetMapping("/get-by-question-set-id/random")
    public List<QuestionDto> getByQuestionSetIdRandomly(@RequestParam int questionSetId, @RequestParam int numberOfQuestions,@RequestParam boolean isPersonalized) {
        return questionService.getByQuestionSetIdRandomly(questionSetId,numberOfQuestions,isPersonalized);
    }
}
