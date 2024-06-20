package core.fu4sbackend.dto;

import core.fu4sbackend.constant.CommentStatus;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Integer id;
    private Date date;
    private String content;
    private CommentStatus status;
    private String username;
    private String account;
    private Integer childrenNumber;
}

