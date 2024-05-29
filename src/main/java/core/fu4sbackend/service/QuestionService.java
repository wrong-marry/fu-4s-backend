package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
                    questionDto.setAnswers(answerService.getByQuestionId(question.getId()));
                    return questionDto;
                })
                .collect(Collectors.toList());
    }
}
