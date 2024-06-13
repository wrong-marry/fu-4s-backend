package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Question;
import core.fu4sbackend.entity.QuestionPriority;
import core.fu4sbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionPriorityRepository extends JpaRepository<QuestionPriority, Integer> {

    @Query(value = "select qp from QuestionPriority qp where qp.user.username = ?2 and qp.question.questionSet.id = ?1")
    public List<QuestionPriority> findByQuestionSetIdAndUsername(int questionSetId, String username);
}
