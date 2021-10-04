package com.kethableez.walkerapi.Notification.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.kethableez.walkerapi.Notification.Model.Notification;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    void deleteByTimestampLessThan(LocalDateTime time);
    List<Notification> findAllByRecieverIdOrderByTimestampAsc(String recieverId, Pageable page);
    // List<Notification> findAllByRecieverIdOrderByTimestampAsc(String recieverId);
    // List<Notification> findAllByRecieverIdAndTimestapmGreaterThan(String recieverId, LocalDateTime timestamp);
}
