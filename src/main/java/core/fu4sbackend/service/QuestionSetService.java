package core.fu4sbackend.service;

import core.fu4sbackend.dto.AnswerDto;
import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.Answer;
import core.fu4sbackend.entity.Question;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionSetService {

    private QuestionSetRepository questionSetRepository;

    @Autowired
    public QuestionSetService(QuestionSetRepository questionSetRepository) {
        this.questionSetRepository = questionSetRepository;
    }

    public QuestionSet getById(int id) {
        return questionSetRepository.getById(id);
    }
    public void save(QuestionSet questionSet) {
        questionSetRepository.save(questionSet);
    }
    public List<QuestionSetDto> getAllQuestionSets() {
        List<QuestionSet> questionSets = questionSetRepository.findAll();
        List<QuestionSetDto> questionSetDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        questionSetDtos = questionSets
                .stream()
                .map(questionSet -> {
                    QuestionSetDto questionSetDto =  modelMapper.map(questionSet, QuestionSetDto.class);
                    questionSetDto.setUsername(questionSet.getUser().getFirstName()+" "+questionSet.getUser().getLastName());
                    return questionSetDto ;
                })
                .collect(Collectors.toList());


        return questionSetDtos;
    }
    public List<QuestionSetDto> getQuestionSetsByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
        List<QuestionSet> questionSetList = questionSetRepository.getAllByUsername(username);
        List<QuestionSetDto> questionSetDtos = new ArrayList<>();
        for(QuestionSet questionSet : questionSetList) {
            QuestionSetDto questionSetDto =  modelMapper.map(questionSet, QuestionSetDto.class);
            questionSetDto.setUsername(questionSet.getUser().getUsername());

            List<QuestionDto> questionDtos = new ArrayList<>();
            for(Question question : questionSet.getQuestions()) {
                QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);

                List<AnswerDto> answerDtos = new ArrayList<>();
                for(Answer answer : question.getAnswers()) {
                    answerDtos.add(modelMapper.map(answer, AnswerDto.class));
                }

                questionDto.setAnswers(answerDtos);
                questionDtos.add(questionDto);
            }

            questionSetDto.setQuestions(questionDtos);
            questionSetDtos.add(questionSetDto);
        }

        return questionSetDtos;
    }

    public void editQuestionSet(QuestionSetDto questionSetDto, String username) throws Exception {
        QuestionSet questionSet = questionSetRepository.findById(questionSetDto.getId())
                .orElse(null);

        if(questionSet == null) throw new Exception("Question Set not found!");

        if(!questionSet.getUser().getUsername().equals(username)) {
            throw new Exception("Username mismatch!");
        }

        ModelMapper modelMapper = new ModelMapper();
        questionSet.setTitle(questionSetDto.getTitle());
        //questionSet.setQuestions((Collection<Question>) questionSetDto.getQuestions());
    }

    public void removeQuestionSet(Integer id, String username) throws Exception {
        QuestionSet questionSet = questionSetRepository.findById(id)
                .orElse(null);

        if(questionSet == null) throw new Exception("Question Set not found!");
        if(!questionSet.getUser().getUsername().equals(username)) {
            throw new Exception("Username mismatch!");
        }

        questionSetRepository.delete(questionSet);
    }
}
