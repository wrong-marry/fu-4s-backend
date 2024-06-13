package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionPriorityDTO;
import core.fu4sbackend.entity.QuestionPriority;
import core.fu4sbackend.repository.QuestionPriorityRepository;
import core.fu4sbackend.repository.QuestionRepository;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionPriorityService {
    private final QuestionPriorityRepository questionPriorityRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    private final QuestionSetService questionSetService;
    @Autowired
    public QuestionPriorityService(QuestionPriorityRepository questionPriorityRepository, UserRepository userRepository, QuestionRepository questionRepository, QuestionSetService questionSetService) {
        this.questionPriorityRepository = questionPriorityRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.questionSetService = questionSetService;
    }

    public List<QuestionPriorityDTO> getAllQuestionPriorityByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
        return questionPriorityRepository.findByUsername(username).stream().map(
                questionPriority -> {
                    return modelMapper.map(questionPriority, QuestionPriorityDTO.class);
                }
        ).toList();
    }

    public void saveAll(List<QuestionPriorityDTO> questionPriorityDTOList) {
        List<QuestionPriority> questionPriorityList = new ArrayList<>();
        for (QuestionPriorityDTO questionPriorityDTO : questionPriorityDTOList) {
            questionPriorityList.add(new QuestionPriority(
                    userRepository.findByUsername(questionPriorityDTO.getUserDto().getUsername()).orElseThrow(
                            () -> new IllegalArgumentException("User not found")
                    ),
                    questionRepository.findById(questionPriorityDTO.getQuestionDto().getId()).orElseThrow(
                            () -> new IllegalArgumentException("Question not found")
                    ),
                    0));
        }
        questionPriorityRepository.saveAll(questionPriorityList);
    }

    public void createQuestionPrioritiesByUsernameAndQuestionSetId(String username, int questionSetId) {

    }
}
