package core.fu4sbackend.dto;

import core.fu4sbackend.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private String code;
    private String name;
    private int semester;
    private ArrayList<Post> posts;
}
