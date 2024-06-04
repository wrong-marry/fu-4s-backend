package core.fu4sbackend.controller;

import core.fu4sbackend.dto.NotificationDto;
import core.fu4sbackend.dto.PostDto;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.service.NotificationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @GetMapping("/getAllByUsername")
    public ResponseEntity<List<NotificationDto>> showAllNotificationsByUsername(@RequestParam String username,
                                                                                 @RequestParam Integer pageNum,
                                                                                @RequestParam Integer pageSize
    ) {
        --pageNum;
        return ResponseEntity.ok(notificationService.getAllByUsername(username, pageNum, pageSize));
    }

    @GetMapping("/getNum")
    public ResponseEntity<Integer> getNumNotifications(@RequestParam String username){
        return ResponseEntity.ok(notificationService.getNumberOfNotifications(username));
    }

    @PutMapping("/{id}/unseen")
    public ResponseEntity<String> markAsUnSeen(@PathVariable("id") String notificationId) {
        try {
            notificationService.markAsUnSeen(Integer.parseInt(notificationId));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Notification marked as unseen successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        }catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Something went wrong while marking notification as unseen");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/markAllAsRead")
    public ResponseEntity<String> markAllAsRead() {
        try {
            notificationService.markAllAsRead();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "All notifications marked as read successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Something went wrong while marking all notifications as read");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}