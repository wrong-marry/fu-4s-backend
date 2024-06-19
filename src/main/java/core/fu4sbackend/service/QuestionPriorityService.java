package core.fu4sbackend.service;

import core.fu4sbackend.dto.QuestionDto;
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

import static java.util.stream.Collectors.toList;

@Service
public class QuestionPriorityService {
    public static final int MAX_QUESTION_PRIORITY = 3;
    private final int MIN_QUESTION_PRIORITY = 0;
    private final QuestionPriorityRepository questionPriorityRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionPriorityService(QuestionPriorityRepository questionPriorityRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.questionPriorityRepository = questionPriorityRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public List<QuestionPriorityDTO> getAllQuestionPriorityByUsernameAndQuestionSetId(String username, int questionSetId) {
        ModelMapper modelMapper = new ModelMapper();
        return questionPriorityRepository.findByQuestionSetIdAndUsername(questionSetId, username).stream().map(
                questionPriority -> {
                    QuestionPriorityDTO questionPriorityDTO = modelMapper.map(questionPriority, QuestionPriorityDTO.class);
                    questionPriorityDTO.setUsername(questionPriority.getUser().getUsername());
                    return questionPriorityDTO;
                }
        ).toList();
    }

    public String initiateQuestionPrioritiesByUsernameAndQuestionSetId(String username, int questionSetId) {
        if (!questionPriorityRepository.findByQuestionSetIdAndUsername(questionSetId, username).isEmpty())
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

    public String updateQuestionPrioritiesByUsernameAndCorrect(String username, List<Integer> correctQuestionsIdList, List<Integer> wrongQuestionIdList) {
        List<QuestionPriority> questionPriorities = new ArrayList<>();
        for (Integer correctQuestionId : correctQuestionsIdList) {
            int currentPriority = questionPriorityRepository.findByUsernameAndQuestionId(username, correctQuestionId).orElseThrow(
                    () -> new IllegalArgumentException("Question priority not found")
            ).getPriority();
            questionPriorities.add(
                    new QuestionPriority(
                            userRepository.findByUsername(username).orElseThrow(
                                    () -> new IllegalArgumentException("User not found")
                            ),
                            questionRepository.findById(correctQuestionId).orElseThrow(
                                    () -> new IllegalArgumentException("Question not found")
                            ),
                            currentPriority==MAX_QUESTION_PRIORITY?MAX_QUESTION_PRIORITY:currentPriority+1
                    )
            );
        }

        for (Integer wrongQuestionId : wrongQuestionIdList) {
            int currentPriority = questionPriorityRepository.findByUsernameAndQuestionId(username, wrongQuestionId).orElseThrow(
                    () -> new IllegalArgumentException("Question priority not found")
            ).getPriority();
            questionPriorities.add(
                    new QuestionPriority(
                            userRepository.findByUsername(username).orElseThrow(
                                    () -> new IllegalArgumentException("User not found")
                            ),
                            questionRepository.findById(wrongQuestionId).orElseThrow(
                                    () -> new IllegalArgumentException("Question not found")
                            ),
                            currentPriority==MIN_QUESTION_PRIORITY?MIN_QUESTION_PRIORITY:currentPriority-1
                    )
            );
        }
        questionPriorityRepository.saveAll(questionPriorities);
        return "Updated Question Priorities";
    }

}
