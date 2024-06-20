package core.fu4sbackend.Notification;

import core.fu4sbackend.controller.NotificationController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class NotificationTests {
    @Autowired
    NotificationController controller;

    @Test
    public void testGetNotification() {
        int num = controller.getNumNotifications("user01").getBody();
        assert(controller
                .showAllNotificationsByUsername("user01",1,num+1)
                .getBody().size()==num);
    }

    @Test
    public void testMarkUnreadNotification() {
        Assertions.assertThrows(Exception.class,()->controller.markNotificationAsUnread(""));
        Assertions.assertThrows(Exception.class,()->controller.markNotificationAsUnread("ab"));
        Assertions.assertThrows(Exception.class,()->controller.markNotificationAsUnread(null));
        assert (controller.markNotificationAsUnread("1").getStatusCode()== HttpStatus.OK);
        assert (controller.markNotificationAsUnread("1").getStatusCode()== HttpStatus.OK);
    }
}
