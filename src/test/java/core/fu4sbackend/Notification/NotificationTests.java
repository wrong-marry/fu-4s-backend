package core.fu4sbackend.Notification;

import core.fu4sbackend.controller.NotificationController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class NotificationTests {
    @Autowired
    NotificationController controller;


    @ParameterizedTest
    @CsvSource({})
    void testGetNotification(String username, int pageNum, int pageSize, Boolean seen) throws Exception {
        assert (controller.showAllNotificationsByUsername(username,pageNum,pageSize,seen).getStatusCode()==HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({})
    void testCountNotification(String username) throws Exception {
        assert (controller.getNumNotifications(username).getStatusCode()==HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({})
    void testSeenNotification(String id) throws Exception {
        assert (controller.markAsSeen(id).getStatusCode()==HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({})
    void testUnseenNotification(String id) throws Exception {
        assert (controller.markAsUnSeen(id).getStatusCode()==HttpStatus.OK);
    }


}
