package core.fu4sbackend.service;

import core.fu4sbackend.dto.DirectNotificationDto;
import core.fu4sbackend.dto.NotificationDto;
import core.fu4sbackend.dto.SubjectDto;
import core.fu4sbackend.entity.Notification;
import core.fu4sbackend.entity.Subject;
import core.fu4sbackend.entity.User;
import core.fu4sbackend.repository.NotificationRepository;
import core.fu4sbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    private static NotificationRepository notificationRepository;
    private static UserRepository userRepository;

    @Autowired
        public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Integer getNumberOfNotifications(String username) {
        return notificationRepository.getAllByUsername(username, null).size();
    }


    public List<NotificationDto> getAllByUsername(String username, Integer pageNum, Integer pageSize, Boolean seen) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        List<Notification> list;

        if (seen != null) {
            list = notificationRepository.getAllByUsernameAndSeen(username, seen, paging);
        } else {
            list = notificationRepository.getAllByUsername(username, paging);
        }

        ModelMapper modelMapper = new ModelMapper();
        return list
                .stream()
                .map(notification -> modelMapper.map(notification, NotificationDto.class))
                .toList();
    }

//    public NotificationDto getById(int id) {
//        Notification notification = notificationRepository.findById(id).orElse(null);
//        if (notification == null) return null;
//
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(notification, NotificationDto.class);
//    }


    public void markAsUnSeen(int notificationId) {
        if (notificationId < 1) {
            throw new IllegalArgumentException("Invalid notification ID");
        }

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        // Update the notification status here
        notification.setSeen(false);
        notificationRepository.save(notification);
    }

    public void markAsSeen(int notificationId) {
        if (notificationId < 1) {
            throw new IllegalArgumentException("Invalid notification ID");
        }

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        // Update the notification status here
        notification.setSeen(true);
        notificationRepository.save(notification);
    }



    @Transactional
    public void markAllAsRead() {
        List<Notification> notifications = notificationRepository.findAll();
        notifications.forEach(notification -> {
            notification.setSeen(true);
        });
        notificationRepository.saveAll(notifications);
    }


    public void deleteNotification(int id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notification not found");
        }
    }
    public static void createDirectNoti(DirectNotificationDto notiDto) throws Exception {
        // Convert NotificationDto to Notification entity
        Notification notification = new Notification();
        notification.setTime(new Date());
        notification.setMessage(notiDto.getMessage());
        notification.setSeen(false); // assuming new notifications are not seen

        // Fetch the user entity
        User user = userRepository.findByUsername(notiDto.getUsername()).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        notification.setUser(user);

        // Save the notification
        notificationRepository.save(notification);
    }

}
