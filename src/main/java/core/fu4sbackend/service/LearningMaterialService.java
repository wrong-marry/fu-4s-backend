package core.fu4sbackend.service;

import core.fu4sbackend.dto.LearningMaterialDto;
import core.fu4sbackend.entity.LearningMaterial;
import core.fu4sbackend.repository.LearningMaterialRepository;
import org.json.JSONArray;
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

    public List<LearningMaterial> getAllLearningMaterials(){
        return learningMaterialRepository.findAll();
    }
    public List<LearningMaterialDto> findByKeyword(String keyword){
        List<LearningMaterial> learningMaterials = learningMaterialRepository.findByKeyword(keyword);
        List<LearningMaterialDto> learningMaterialDtos;

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

}

