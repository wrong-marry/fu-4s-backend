package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Integer id;
    private Date time;
    private String message;
    private boolean isSeen;
    private Integer postId;
}
