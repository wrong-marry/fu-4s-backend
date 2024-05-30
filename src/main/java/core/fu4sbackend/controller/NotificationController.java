package core.fu4sbackend.controller;

import core.fu4sbackend.dto.NotificationDto;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/getAll")
    public List<NotificationDto> getAllNotifications() {
        List<NotificationDto> notifications = notificationService.getAllNotificationDtos();
        return notifications;
    }

    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<NotificationDto>> showAllNotificationsByUsername(@RequestParam String username) {
        return ResponseEntity.ok(notificationService.getAllByUsername(username));
    }
}