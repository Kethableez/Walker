package com.kethableez.walkerapi.Notification.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.kethableez.walkerapi.Notification.Model.Action;
import com.kethableez.walkerapi.Notification.Model.DataPackage;
import com.kethableez.walkerapi.Notification.Model.Notification;
import com.kethableez.walkerapi.Notification.Repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    @Autowired
    private final NotificationRepository notificationRepository;

    public void notificationTrigger(String sender, String reciever, Action action, DataPackage request) {
        Notification notification = new Notification(sender, reciever, action, LocalDateTime.now(), false);
        notificationRepository.save(notification);
    }

    public void clearOldNotification() {
        notificationRepository.deleteByTimestampLessThan(LocalDateTime.now().minusMonths(1L));
    }

    public List<Notification> getLatestNotifications(String reciever) {
        Pageable page = PageRequest.of(0, 5);
        return notificationRepository.findAllByRecieverIdOrderByTimestampAsc(reciever, page);
    }

    public void markAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).get();
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    // public List<Notification> getNotifications(String recieverId, LocalDateTime timestamp, boolean isInit) {
    //     if(isInit) return this.notificationRepository.findAllByRecieverIdOrderByTimestampAsc(recieverId);
    //     else return this.notificationRepository.findAllByRecieverIdAndTimestapmGreaterThan(recieverId, timestamp);
    // }
}
