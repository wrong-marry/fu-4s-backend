package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionPriorityDTO;
import core.fu4sbackend.entity.Question;
import core.fu4sbackend.entity.QuestionPriority;
import core.fu4sbackend.repository.QuestionPriorityRepository;
import core.fu4sbackend.repository.QuestionRepository;
import core.fu4sbackend.repository.QuestionSetRepository;
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

    private final QuestionSetRepository questionSetRepository;

    @Autowired
    public QuestionPriorityService(QuestionPriorityRepository questionPriorityRepository, UserRepository userRepository, QuestionRepository questionRepository, QuestionSetRepository questionSetRepository) {
        this.questionPriorityRepository = questionPriorityRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.questionSetRepository = questionSetRepository;
    }

    public List<QuestionPriorityDTO> getAllQuestionPriorityByUsernameAndQuestionSetId(String username, int questionSetId) {
        ModelMapper modelMapper = new ModelMapper();
        return questionPriorityRepository.findByQuestionSetIdAndUsername(questionSetId,username).stream().map(
                questionPriority -> {
                    QuestionPriorityDTO questionPriorityDTO = modelMapper.map(questionPriority, QuestionPriorityDTO.class);
                    questionPriorityDTO.setUsername(questionPriority.getUser().getUsername());
                    return questionPriorityDTO;
                }
        ).toList();
    }

    public String initiateQuestionPrioritiesByUsernameAndQuestionSetId(String username, int questionSetId) {
        if(!questionPriorityRepository.findByQuestionSetIdAndUsername(questionSetId,username).isEmpty())
            return "Already initiated";
        List<Question> questions = questionRepository.getByQuestionSetId(questionSetId);
        List<QuestionPriority> questionPriorities = new ArrayList<>();
        for (Question question : questions) {
            questionPriorities.add(
                    new QuestionPriority(userRepository.findByUsername(username).orElseThrow(
                            () -> new IllegalArgumentException("User not found")
                    ),
                            question,
                            0)
            );
        }
        questionPriorityRepository.saveAll(questionPriorities);
        return "Initiated Question Priorities";
    }

    public String updateQuestionPrioritiesByUsernameAndCorrect(String username, List<Integer> correctQuestionsId, List<Integer> wrongQuestionId) {

        return "Updated Question Priorities";
    }

}
