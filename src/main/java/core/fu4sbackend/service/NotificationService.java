package core.fu4sbackend.service;

import core.fu4sbackend.dto.NotificationDto;
import core.fu4sbackend.dto.QuestionSetDto;
import core.fu4sbackend.entity.Notification;
import core.fu4sbackend.entity.QuestionSet;
import core.fu4sbackend.repository.NotificationRepository;
import core.fu4sbackend.repository.QuestionSetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationDto> getAllNotificationDtos() {
        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationDto> notificationDtos = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        notificationDtos = notifications
                .stream()
                .map(notification -> {
                    return modelMapper.map(notification, NotificationDto.class);
                })
                .collect(Collectors.toList());

        return notificationDtos;
    }
}
