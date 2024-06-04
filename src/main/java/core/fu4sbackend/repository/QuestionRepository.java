package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query(value = "select q from Question q where q.questionSet.id = ?1")
    List<Question> getByQuestionSetId(int questionSetId);
}
