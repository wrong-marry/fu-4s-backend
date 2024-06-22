package core.fu4sbackend.Notification;

import core.fu4sbackend.Fu4sBackendApplicationTests;
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
    @CsvSource({"user04,1,1,,NORMAL",
            "user04,1,1,,EXCEPTION",
            "user-04,1,1,,EXCEPTION",
            "user04,0,1,,EXCEPTION",
            "user04,1,0,,EXCEPTION",
            "user04,1,1,TRUE,NORMAL",
            "user04,1,1,FALSE,NORMAL",
            "'',1,1,,NORMAL",
            ",1,1,,NORMAL",})
    void testGetNotification(String username, int pageNum, int pageSize, Boolean seen, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) throws Exception {
        if (expectedTestResult == Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
            assert (controller.showAllNotificationsByUsername(username,pageNum,pageSize,seen).getStatusCode()==HttpStatus.OK);
        else {
            Assertions.assertThrows(Exception.class,()->controller.showAllNotificationsByUsername(username,pageNum,pageSize,seen));
        }
    }

    @ParameterizedTest
    @CsvSource({"user04,NORMAL",
            "user-04,EXCEPTION",
            ",EXCEPTION",
            "'',EXCEPTION"})
    void testCountNotification(String username, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) throws Exception {
        assert (controller.getNumNotifications(username).getStatusCode()==HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({"1,NORMAL",
            "-1,EXCEPTION",
            ",EXCEPTION",
            "'',EXCEPTION"})
    void testSeenNotification(String id, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) throws Exception {
        if (expectedTestResult == Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        assert (controller.markAsSeen(id).getStatusCode()==HttpStatus.OK);
        else {
            Assertions.assertThrows (Exception.class,()->controller.markAsSeen(id));
        }
    }

    @ParameterizedTest
    @CsvSource({"1,NORMAL",
            "-1,EXCEPTION",
            ",EXCEPTION",
            "'',EXCEPTION"})
    void testUnseenNotification(String id, Fu4sBackendApplicationTests.ExpectedTestResult expectedTestResult) throws Exception {
        if (expectedTestResult == Fu4sBackendApplicationTests.ExpectedTestResult.NORMAL)
        assert (controller.markAsUnSeen(id).getStatusCode()==HttpStatus.OK);
        else {
            Assertions.assertThrows (Exception.class,()->controller.markAsUnSeen(id));
        }
    }


}
