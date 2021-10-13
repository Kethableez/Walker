package com.kethableez.walkerapi.Notification.Controller;

import com.kethableez.walkerapi.Utility.Response.MessageResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    

    // @PostMapping("/trigger/{sender}/{reciever}/{action}")
    // public void notificationTrigger(
    //     @PathVariable("sender") String sender,
    //     @PathVariable("reviever") String reciever,
    //     @PathVariable("action") Action action
    // ) {

    // }

    @GetMapping("/get_notification")
    public ResponseEntity<MessageResponse> getNotifiaction() {
        return new ResponseEntity<>(new MessageResponse("test"), HttpStatus.OK);
    }
}
