package core.fu4sbackend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LearningMaterialDto extends PostDto{
    private String content;
}
