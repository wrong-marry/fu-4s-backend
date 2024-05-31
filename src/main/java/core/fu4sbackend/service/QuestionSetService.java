package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<QuestionSetDto> getQuestionSetsByUsername(String username, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("postTime").descending());

        ModelMapper modelMapper = new ModelMapper();
        return questionSetRepository.getAllByUsername(username, paging)
                .stream().map(learningMaterial -> modelMapper.map(learningMaterial, QuestionSetDto.class))
                .toList();
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

    public Integer getNumberOfQuestionSets(String username) {
        return questionSetRepository.getAllByUsername(username, null).size();
    }
}
