package core.fu4sbackend.dto;

import core.fu4sbackend.constant.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PostDto {
    private Integer id;
    private Date postTime;
    private String title;
    private boolean isTest;
    private PostStatus status;

    private String username;
    private String subjectCode;
}
