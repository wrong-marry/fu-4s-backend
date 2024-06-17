package core.fu4sbackend.service;

import core.fu4sbackend.dto.NotificationDto;
import core.fu4sbackend.entity.Notification;
import core.fu4sbackend.repository.NotificationRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
        public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Integer getNumberOfNotifications(String username) {
        return notificationRepository.getAllByUsername(username, null).size();
    }


    public List<NotificationDto> getAllByUsername(String username, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        List<Notification> list = notificationRepository.getAllByUsername(username,paging);
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

    public void markNotificationAsUnread(String id) {
        Notification notification = notificationRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id " + id));
        notification.setSeen(false);
        notificationRepository.save(notification);
    }



}
