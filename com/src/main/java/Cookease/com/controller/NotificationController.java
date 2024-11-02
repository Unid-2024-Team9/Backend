package Cookease.com.controller;

import Cookease.com.service.FcmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final FcmService fcmService;

    public NotificationController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @GetMapping("/sendNotification")
    public String sendNotification(@RequestParam String token, @RequestParam String title, @RequestParam String body) {
        fcmService.sendNotification(token, title, body);
        return "Notification sent!";
    }
}

