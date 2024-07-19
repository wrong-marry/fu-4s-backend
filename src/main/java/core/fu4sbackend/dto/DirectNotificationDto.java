package core.fu4sbackend.dto;

import core.fu4sbackend.constant.NotificationMessage;
import core.fu4sbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectNotificationDto {
    private String message;
    private String username ;

}
