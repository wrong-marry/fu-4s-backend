package core.fu4sbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LearningMaterialRequestDto {
    private String title;
    private String content;
    private String subjectCode;
    private List<MultipartFile> files;
    private String username;
}
