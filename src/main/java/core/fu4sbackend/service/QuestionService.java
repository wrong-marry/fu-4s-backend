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
    private final int PROBABLITY_GENERATOR_RANGE = 1000;
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
                    return questionDto;
                })
                .collect(Collectors.toList());
    }

    public List<QuestionDto> getByQuestionSetIdRandomly(int questionSetId, int numberOfQuestions, boolean isPersonalized) {
        Random rand = new Random();
        List<QuestionDto> allQuestions = getByQuestionSetId(questionSetId);
        List<QuestionDto> randomQuestions = new ArrayList<>();

        if(!isPersonalized)
            addToRandomQuestions(rand,allQuestions,randomQuestions,numberOfQuestions);

        else{
            int generatedRandomNumber = rand.nextInt(PROBABLITY_GENERATOR_RANGE);
            
        }
        return randomQuestions;
    }

    public void addToRandomQuestions(Random rand, List<QuestionDto> allQuestions, List<QuestionDto> randomQuestions, int numberOfQuestions) {

        for (int i = 0; i < numberOfQuestions; i++) {
            int randomIndex = rand.nextInt(allQuestions.size());
            QuestionDto questionDto = allQuestions.get(randomIndex);
            questionDto.randomlyOrderAnswers();
            randomQuestions.add(questionDto);
            allQuestions.remove(randomIndex);
        }
    }

    public QuestionDto getById(int questionId) {
        ModelMapper modelMapper = new ModelMapper();
        QuestionDto questionDto = modelMapper.map(questionRepository.findById(questionId),QuestionDto.class);
        return questionDto;
    }

}
