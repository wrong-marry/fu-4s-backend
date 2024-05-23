package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private String code;
    private String name;
    private int semester;
//    private ArrayList<Post> posts;
}
