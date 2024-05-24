package core.fu4sbackend.service;

import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.repository.LearningMaterialRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearningMaterialService {
    @Autowired
    private LearningMaterialRepository learningMaterialRepository;

    public List<LearningMaterialDto> getAllLearningMaterials() {
        List<LearningMaterial> learningMaterials = learningMaterialRepository.findAll();
        List<LearningMaterialDto> learningMaterialDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        learningMaterialDtos = learningMaterials
                .stream()
                .map(learningMaterial -> {
                    LearningMaterialDto learningMaterialDto = modelMapper.map(learningMaterial, LearningMaterialDto.class);
                    learningMaterialDto.setUsername(learningMaterial.getUser().getFirstName() + " " + learningMaterial.getUser().getLastName());
                    return learningMaterialDto;
                })
                .collect(Collectors.toList());

        return learningMaterialDtos;
    }


    public LearningMaterialDto getLearningMaterialById(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<LearningMaterial> optionalLearningMaterial = learningMaterialRepository.findById(id);

        if (optionalLearningMaterial.isPresent()) {
            LearningMaterial learningMaterial = optionalLearningMaterial.get();
            LearningMaterialDto learningMaterialDto = modelMapper.map(learningMaterial, LearningMaterialDto.class);
            learningMaterialDto.setUsername(learningMaterial.getUser().getFirstName() + " " + learningMaterial.getUser().getLastName());
            return learningMaterialDto;
        } else {
            throw new ResourceNotFoundException("Learning material not found with id: " + id);
        }
    }
}