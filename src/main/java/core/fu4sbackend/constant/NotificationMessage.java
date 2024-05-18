package core.fu4sbackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationMessage {
    APPROVED_POST("Your post have been approved"),
    DISAPPROVED_POST("Your post has been disapproved"),
    HIDE_COMMENT("Your comment has been hidden");

    private final String message;
}
