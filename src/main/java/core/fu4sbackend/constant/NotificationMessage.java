package core.fu4sbackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationMessage {
    APPROVED_POST,
    DISAPPROVED_POST,
    POST_COMMENTED,
    COMMENT_REPLY,
    HIDE_COMMENT;
}
