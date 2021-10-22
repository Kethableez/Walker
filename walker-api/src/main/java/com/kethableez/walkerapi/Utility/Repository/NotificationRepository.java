package com.kethableez.walkerapi.Utility.Repository;

import java.util.List;

import com.kethableez.walkerapi.Utility.Model.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String>{
    List<Notification> findByRecieverIdOrderByTimestampAsc(String userId);
}
