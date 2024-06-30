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
                                                                                @RequestParam Integer pageSize,
                                                                                @RequestParam(required = false) Boolean seen
    ) {
        --pageNum;
        return ResponseEntity.ok(notificationService.getAllByUsername(username, pageNum, pageSize, seen));
    }

    @GetMapping("/getNum")
    public ResponseEntity<Integer> getNumNotifications(@RequestParam String username){
        return ResponseEntity.ok(notificationService.getNumberOfNotifications(username));
    }

    @PutMapping("/{id}/unseen")
    public ResponseEntity<String> markAsUnSeen(@PathVariable("id") String notificationId) {
            notificationService.markAsUnSeen(Integer.parseInt(notificationId));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Notification marked as unseen successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
    @PutMapping("/{id}/seen")
    public ResponseEntity<String> markAsSeen(@PathVariable("id") String notificationId) throws IllegalArgumentException {
        notificationService.markAsSeen(Integer.parseInt(notificationId));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Notification marked as seen successfully");
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }


    @PutMapping("/markAllAsRead")
    public ResponseEntity<String> markAllAsRead() {
            notificationService.markAllAsRead();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "All notifications marked as read successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable int id) {
        notificationService.deleteNotification(id);
    }

}