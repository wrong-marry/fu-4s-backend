package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return questionSetRepository.getAllByUsername(username)
                .stream().map(questionSet -> modelMapper.map(questionSet, QuestionSetDto.class))
                .toList();
    }
}
