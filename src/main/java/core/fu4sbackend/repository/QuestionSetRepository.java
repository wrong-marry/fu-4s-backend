package core.fu4sbackend.repository;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.QuestionSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSetRepository extends JpaRepository<QuestionSet, Integer> {
    public QuestionSet findById(int id);
    @Query("select q from QuestionSet q where q.user.username = ?1")
    public List<QuestionSet> getAllByUsername(String username);
    @Query(value = "select q from QuestionSet q where q.title like concat('%',:keyword,'%')")
    public List<QuestionSet> findByKeyword(String keyword);
}
