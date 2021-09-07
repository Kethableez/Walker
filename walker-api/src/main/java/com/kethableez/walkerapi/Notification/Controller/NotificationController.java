package com.kethableez.walkerapi.Notification.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kethableez.walkerapi.Notification.NotificationDTO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    public Map<String, SseEmitter> emitters = new HashMap<>();

    @CrossOrigin
    @GetMapping(value = "/subcribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam("userId") String userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        sendInitEvent(emitter);
        emitters.put(userId, emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }

    @PostMapping("/dispatchEvent")
    public void dispatchEvent(@RequestBody NotificationDTO nDto) {
        List<SseEmitter> sEmitters = new ArrayList<>();
        for (String id : nDto.getUIds()) sEmitters.add(emitters.get(id));
        if (sEmitters != null) {
            for (SseEmitter e : sEmitters) {
                if (e != null) {
                    try {
                        e.send(SseEmitter.event().name(nDto.getAction()).data(nDto.getText()));
                    }
                    catch (IOException ex) {
                        emitters.remove(e);
                    }
                }

            }
        }

    }

    private void sendInitEvent(SseEmitter emitter) {
        try {
            emitter.send(SseEmitter.event().name("INIT"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
