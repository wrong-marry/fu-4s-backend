package core.fu4sbackend.repository;

import core.fu4sbackend.entity.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {
    @Query("select l from LearningMaterial l where l.user.username = ?1")
    public List<LearningMaterial> getAllByUsername(String username);
}
