package core.fu4sbackend.service;

import core.fu4sbackend.dto.AnswerDto;
import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, AnswerService answerService) {
        this.questionRepository = questionRepository;
        this.answerService = answerService;
    }

    public List<QuestionDto> getByQuestionSetId(int questionSetId){
        ModelMapper modelMapper = new ModelMapper();
        return questionRepository.getByQuestionSetId(questionSetId).stream()
                .map(question -> {
                    QuestionDto questionDto = modelMapper.map(question,QuestionDto.class);
                    List<AnswerDto> answerDtoList = answerService.getByQuestionId(question.getId());
                    questionDto.setAnswers(answerDtoList);
                    return questionDto;
                })
                .collect(Collectors.toList());
    }
    public List<QuestionDto> getByQuestionSetIdRandomly(@RequestParam int questionSetId, @RequestParam int numberOfQuestions) {
        Random rand = new Random();
        List<QuestionDto> allQuestions = getByQuestionSetId(questionSetId);
        List<QuestionDto> randomQuestions = new ArrayList<>();

        for (int i = 0; i < numberOfQuestions; i++) {
            int randomIndex = rand.nextInt(allQuestions.size());
            QuestionDto questionDto = allQuestions.get(randomIndex);
            questionDto.randomlyOrderAnswers();
            randomQuestions.add(questionDto);
            allQuestions.remove(randomIndex);
        }
        return randomQuestions;
    }
}
