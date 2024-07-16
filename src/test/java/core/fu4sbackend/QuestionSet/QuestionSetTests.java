package core.fu4sbackend.QuestionSet;

import core.fu4sbackend.controller.QuestionSetController;
import core.fu4sbackend.dto.AnswerDto;
import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.service.QuestionSetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QuestionSetTests {
    @Autowired
    QuestionSetController controller;
    @Autowired
    QuestionSetService service;

    @Test
    void getQuestionSet() {
        List<AnswerDto> answers = new ArrayList<>(List.of(
                new AnswerDto(11,"a",true),
                new AnswerDto(12,"a",true)
        ));
        QuestionDto questionDto = new QuestionDto(33, "Test", answers);
        answers = new ArrayList<>(List.of(new AnswerDto(13,"d",true),
                new AnswerDto(14,"e",true)));
        QuestionDto questionDto2 = new QuestionDto(34, "Test2", answers);
        Assertions.assertThrows(Exception.class,()->service.addNewQuestionSet("Test", "SWP391", List.of(questionDto2,questionDto), "user07"));
    }
}
