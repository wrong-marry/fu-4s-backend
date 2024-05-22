package core.fu4sbackend.repository;

import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.QuestionSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSetRepository extends JpaRepository<QuestionSet, Integer> {
    public QuestionSet findById(int id);

}
