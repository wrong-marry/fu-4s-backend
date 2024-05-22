package core.fu4sbackend.service;

import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.LearningMaterialRepository;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LearningMaterialService {
    @Autowired
    private LearningMaterialRepository learningMaterialRepository;
    public List<LearningMaterial> getAllLearningMaterials(){
        return learningMaterialRepository.findAll();
    }
}

