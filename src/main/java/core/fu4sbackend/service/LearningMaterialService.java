package core.fu4sbackend.service;

import core.fu4sbackend.constant.PostStatus;
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

    public List<LearningMaterialDto> getAllLearningMaterials(){
        List<LearningMaterialDto> learningMaterials = new ArrayList<>();
        for(LearningMaterial learningMaterial : learningMaterialRepository.findAll()){
            LearningMaterialDto learningMaterialDto = new LearningMaterialDto();

            learningMaterialDto.setId(learningMaterial.getId());
            learningMaterialDto.setContent(learningMaterial.getContent());
            learningMaterialDto.setStatus(learningMaterial.getStatus());
            learningMaterialDto.setUsername(learningMaterial.getUser().getUsername());
            learningMaterialDto.setTest(learningMaterial.isTest());
            learningMaterialDto.setPostTime(learningMaterial.getPostTime());
            learningMaterialDto.setTitle(learningMaterial.getTitle());
            learningMaterials.add(learningMaterialDto);
        }
        return learningMaterials;
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

