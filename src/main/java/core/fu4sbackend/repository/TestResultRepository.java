package core.fu4sbackend.repository;

import core.fu4sbackend.dto.TestResultDto;
import core.fu4sbackend.dto.UserDto;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.entity.TestResult;
import core.fu4sbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {

    public TestResult findTopByUserAndQuestionSetOrderByIdDesc(User user, QuestionSet questionSet);
    public List<TestResult> findByUser(User user);
}
