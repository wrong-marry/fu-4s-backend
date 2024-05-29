package core.fu4sbackend.service;

import core.fu4sbackend.dto.AnswerDto;
import core.fu4sbackend.repository.AnswerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    public List<AnswerDto> getByQuestionId(int questionId){
        ModelMapper modelMapper = new ModelMapper();
        List<AnswerDto> answerDtoList = answerRepository.getByQuestionId(questionId)
                .stream().map(answer -> {
                    AnswerDto answerDto = modelMapper.map(answer,AnswerDto.class);
                    return answerDto;
                })
                .collect(Collectors.toList());
        return answerDtoList;

    }
}
