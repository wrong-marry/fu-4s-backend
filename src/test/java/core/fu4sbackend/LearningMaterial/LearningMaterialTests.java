package core.fu4sbackend.LearningMaterial;

import core.fu4sbackend.controller.LearningMaterialController;
import core.fu4sbackend.controller.PostController;
import core.fu4sbackend.dto.LearningMaterialDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LearningMaterialTests {
    @Autowired
    LearningMaterialController controller;

    @Test
    void getLearningMaterial() {
        LearningMaterialDto learningMaterialDto =controller
                .getAllLearningMaterialsByUsername("user01",1,99).getBody().getFirst();
        assert(controller.getLearningMaterialById(learningMaterialDto.getId()+"").getBody().getContent().equals(learningMaterialDto.getContent()));
       }

    @Test
    void getInvalidLearningMaterial() {
        Assertions.assertThrows(Exception.class,()->controller.getLearningMaterialById("aa"));
        Assertions.assertThrows(Exception.class,()->controller.getLearningMaterialById(null));
    }
}
