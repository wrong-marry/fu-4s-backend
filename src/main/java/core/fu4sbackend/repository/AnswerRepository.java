package core.fu4sbackend.repository;

import core.fu4sbackend.dto.QuestionDto;
import core.fu4sbackend.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query(value = "select a from Answer a where a.question.id = ?1")
    List<Answer> getByQuestionId(int questionId);
}
