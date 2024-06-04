package core.fu4sbackend.service;

import core.fu4sbackend.constant.PostStatus;
import core.fu4sbackend.dto.AnswerDto;
import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.Answer;
import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.entity.Question;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.Answer;
import core.fu4sbackend.entity.Question;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionSetService {

    private final QuestionSetRepository questionSetRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public QuestionSetService(QuestionSetRepository questionSetRepository, SubjectRepository subjectRepository, UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionSetRepository = questionSetRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public QuestionSetDto getById(int id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(questionSetRepository.findById(id), QuestionSetDto.class);
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
                    QuestionSetDto questionSetDto = modelMapper.map(questionSet, QuestionSetDto.class);
                    questionSetDto.setUsername(questionSet.getUser().getFirstName() + " " + questionSet.getUser().getLastName());

                    return questionSetDto;

                })
                .collect(Collectors.toList());


        return questionSetDtos;
    }


    public QuestionSetDto getQuestionSetById(Integer id) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Optional<QuestionSet> optionalQuestionSet = questionSetRepository.findById(id);

        if (optionalQuestionSet.isPresent()) {
            QuestionSet questionSet = optionalQuestionSet.get();
            QuestionSetDto questionSetDto = modelMapper.map(questionSet, QuestionSetDto.class);
            questionSetDto.setUsername(questionSet.getUser().getFirstName() + " " + questionSet.getUser().getLastName());
            return questionSetDto;
        } else {
            throw new Exception("Question Set not found with id: " + id);
        }
    }
  
    public List<QuestionSetDto> getQuestionSetsByUsername(String username, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("postTime").descending());

        ModelMapper modelMapper = new ModelMapper();
        return questionSetRepository.getAllByUsername(username, paging)
                .stream().map(questionSet -> {
                    QuestionSetDto questionSetDto = modelMapper.map(questionSet, QuestionSetDto.class);
                    questionSetDto.setUsername(questionSet.getUser().getUsername());
                    return questionSetDto;
                })
                .toList();
    }

    public void editQuestionSet(QuestionSetDto questionSetDto, String username) throws Exception {
        QuestionSet questionSet = questionSetRepository.findById(questionSetDto.getId())
                .orElse(null);

        if (questionSet == null) throw new Exception("Question Set not found!");

        if (!questionSet.getUser().getUsername().equals(username)) {
            throw new Exception("Username mismatch!");
        }

        ModelMapper modelMapper = new ModelMapper();
        questionSet.setTitle(questionSetDto.getTitle());
        //questionSet.setQuestions((Collection<Question>) questionSetDto.getQuestions());
    }

    public void removeQuestionSet(Integer id, String username) throws Exception {
        QuestionSet questionSet = questionSetRepository.findById(id)
                .orElse(null);

        if (questionSet == null) throw new Exception("Question Set not found!");
        if (!questionSet.getUser().getUsername().equals(username)) {
            throw new Exception("Username mismatch!");
        }

        questionSetRepository.delete(questionSet);
    }

    public Integer getNumberOfQuestionSets(String username) {
        return questionSetRepository.getAllByUsername(username, null).size();
    }

    @Transactional
    public QuestionSetDto addNewQuestionSet(
            String title,
            String subjectCode,
            List<QuestionDto> questionDtoList,
            String username
    ) {
        ModelMapper modelMapper = new ModelMapper();

        //1: questionSet table
        QuestionSet questionSet = new QuestionSet();
        questionSet.setTitle(title);
        questionSet.setSubject(subjectRepository.findById(subjectCode).orElseThrow());
        questionSet.setUser(userRepository.findByUsername(username).orElseThrow());
        questionSet.setPostTime(new Date(System.currentTimeMillis()));
        questionSet.setStatus(PostStatus.PENDING_APPROVE);
        questionSet.setAttempts(0);
        questionSet.setTest(true);
        questionSet = questionSetRepository.save(questionSet);

        //2: question table
        QuestionSet finalQuestionSet = questionSet;
        List<Question> questions = questionDtoList.stream().map(questionDto -> {
            Question question = modelMapper.map(questionDto, Question.class);
            question.setQuestionSet(questionSetRepository.findById(finalQuestionSet.getId()).orElseThrow());

            return question;
        }).toList();
        questionRepository.saveAll(questions);

        //3: answer table
        List<Answer> answers = new ArrayList<>();
        questionDtoList = questions.stream().map(question -> modelMapper.map(question, QuestionDto.class)).toList();

        for (QuestionDto questionDto : questionDtoList) {
            List<AnswerDto> answerDtos = questionDto.getAnswers();
            for (AnswerDto answerDto : answerDtos) {
                Answer answer = modelMapper.map(answerDto, Answer.class);
                answer.setQuestion(questionRepository.findById(questionDto.getId()).orElseThrow());

                answers.add(answer);
            }
        }
        answerRepository.saveAll(answers);

        return modelMapper.map(questionSet, QuestionSetDto.class);
    }

    public boolean isValidUser(String username, Integer id) {
        return questionSetRepository.findById(id).orElseThrow().getUser().getUsername().equals(username);
    }
}
