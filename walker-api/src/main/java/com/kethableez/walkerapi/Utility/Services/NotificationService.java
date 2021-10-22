package com.kethableez.walkerapi.Utility.Services;

import java.time.LocalDateTime;
import java.util.List;

import com.kethableez.walkerapi.Utility.Model.Notification;
import com.kethableez.walkerapi.Utility.Model.NotificationType;
import com.kethableez.walkerapi.Utility.Repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(String senderId, String recieverId, String additionalId, NotificationType type) {
        Notification notification = new Notification(senderId, recieverId, additionalId, type, LocalDateTime.now(), false);
        notificationRepository.save(notification);
    }

    public void markAsRead(String notificationId) {
        Notification notification = this.notificationRepository.findById(notificationId).get();
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByRecieverIdOrderByTimestampAsc(userId);
    }
}
