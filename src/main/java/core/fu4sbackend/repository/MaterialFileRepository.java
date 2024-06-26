package core.fu4sbackend.repository;

import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.entity.MaterialFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialFileRepository extends JpaRepository<MaterialFile, Integer> {
    public List<MaterialFile> findAllByLearningMaterial(LearningMaterial learningMaterial);

    public MaterialFile findByLearningMaterialAndFilename(LearningMaterial learningMaterial, String filename);

    List<MaterialFile> findByLearningMaterialId(Integer materialId);

    public List<MaterialFile> deleteByLearningMaterial(LearningMaterial learningMaterial);
}
