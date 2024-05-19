package core.fu4sbackend.dto;

import core.fu4sbackend.constant.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer id;
    private Date date;
    private String content;
    private CommentStatus status;
}

