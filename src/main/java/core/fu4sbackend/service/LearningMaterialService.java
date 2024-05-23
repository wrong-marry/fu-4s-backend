package core.fu4sbackend.service;

import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.LearningMaterialRepository;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LearningMaterialService {
    @Autowired
    private LearningMaterialRepository learningMaterialRepository;
    public List<LearningMaterialDto> getAllLearningMaterials(){

        List<LearningMaterial> learningMaterials = learningMaterialRepository.findAll();
        List<LearningMaterialDto> learningMaterialDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        learningMaterialDtos = learningMaterials
                .stream()
                .map(learningMaterial -> {
                    LearningMaterialDto learningMaterialDto =  modelMapper.map(learningMaterial, LearningMaterialDto.class);
                    learningMaterialDto.setUsername(learningMaterial.getUser().getFirstName()+" "+learningMaterial.getUser().getLastName());
                    return learningMaterialDto ;
                })
                .collect(Collectors.toList());
        return learningMaterialDtos;

    }

//    for (LearningMaterial learningMaterial : questionSets) {
//        LearningMaterial learningMaterial = modelMapper.map(questionSet, QuestionSetDto.class);
//        // questionSetDto.setUsername(questionSet.getUser().getUsername());
//        questionSetDto.setUsername(questionSet.getUser().getFirstName()+" "+questionSet.getUser().getLastName() );
//        questionSetDtos.add(questionSetDto);
//    }

}

