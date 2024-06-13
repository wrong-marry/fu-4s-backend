package core.fu4sbackend.repository;

import core.fu4sbackend.entity.QuestionPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionPriorityRepository extends JpaRepository<QuestionPriority, Integer> {
    public void saveAll(List<QuestionPriority> questionPriorityList);
    public List<QuestionPriority> findByUsername(String username);
}
