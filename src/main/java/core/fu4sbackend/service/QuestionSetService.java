package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.QuestionRepository;
import core.fu4sbackend.repository.QuestionSetRepository;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

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
        for (QuestionSet questionSet : questionSets) {
            QuestionSetDto questionSetDto = modelMapper.map(questionSet, QuestionSetDto.class);
           // questionSetDto.setUsername(questionSet.getUser().getUsername());
            questionSetDto.setUsername(questionSet.getUser().getFirstName()+" "+questionSet.getUser().getLastName() );
            questionSetDtos.add(questionSetDto);
        }

        return questionSetDtos;
    }
}
