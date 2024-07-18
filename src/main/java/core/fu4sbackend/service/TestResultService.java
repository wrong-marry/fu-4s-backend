package core.fu4sbackend.service;

import core.fu4sbackend.dto.TestResultDto;
import core.fu4sbackend.entity.TestResult;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.QuestionSetRepository;
import core.fu4sbackend.repository.TestResultRepository;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TestResultService {
    private final TestResultRepository testResultRepository;
    private final UserRepository userRepository;
    private final QuestionSetRepository questionSetRepository;

    @Autowired
    public TestResultService(TestResultRepository testResultRepository, UserRepository userRepository, QuestionSetRepository questionSetRepository) {
        this.testResultRepository = testResultRepository;
        this.userRepository = userRepository;
        this.questionSetRepository = questionSetRepository;
    }

    public List<TestResultDto> getTestResultsByUser(String username) {
        ModelMapper modelMapper = new ModelMapper();
        return testResultRepository.findByUser(userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found")
        )).stream().map(testResult -> {
            TestResultDto testResultDto = modelMapper.map(testResult, TestResultDto.class);
            testResultDto.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(testResult.getDate()));
            testResultDto.setUsername(testResult.getUser().getUsername());
            return testResultDto;
        }).toList().reversed();
    }

    public List<TestResultDto> getTestResultsByUsernameAndPersonalized(boolean personalized, String username) {
        return getTestResultsByUser(username).stream().filter(
                test -> test.isPersonalized()==personalized
        ).toList();
    }

    public TestResultDto getFirstTestResultByUserAndQuestionSet(String username, int questionSetId) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(testResultRepository.findTopByUserAndQuestionSetOrderByIdDesc(userRepository.findByUsername(username).orElseThrow(
                        () -> new RuntimeException("User not found")),
                questionSetRepository.findById(questionSetId)
        ), TestResultDto.class);
    }

    public float getLastScoreByUserAndQuestionSetId(String username, int questionSetId) {
        return getFirstTestResultByUserAndQuestionSet(username, questionSetId).getResult();
    }

    public TestResultDto saveTestResult(float score, String username, int questionSetId, boolean isPersonalized) {
        ModelMapper modelMapper = new ModelMapper();
        TestResult testResult = new TestResult();
        testResult.setResult(score);
        testResult.setPersonalized(isPersonalized);
        testResult.setQuestionSet(questionSetRepository.findById(questionSetId));
        testResult.setUser(userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found")
        ));
        testResult.setDate(new Date(System.currentTimeMillis()));
        return modelMapper.map(testResultRepository.save(testResult), TestResultDto.class);
    }

    public Integer getNumberOfTestResults() {
        return testResultRepository.findAll().size();
    }

    public int getNumberOfTestResultsThisMonth() {
            YearMonth currentMonth = YearMonth.now();
            LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
            LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

            Date startDate = Date.from(startOfMonth.atZone(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(endOfMonth.atZone(ZoneId.systemDefault()).toInstant());

            return testResultRepository.countTestResultsByDateBetween(startDate, endDate);}
        public double calculatePercentageChangeTestResult(int oldValue, int newValue) {
            if (oldValue == 0) {
                return newValue > 0 ? 100.0 : 0.0;
            }
            return (newValue * 100.0) / oldValue;
        }


}
