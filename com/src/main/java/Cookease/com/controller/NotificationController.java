package Cookease.com.controller;

import Cookease.com.service.FcmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Notification Controller", description = "APIs for managing and sending notifications")
public class NotificationController {

    private final FcmService fcmService;

    public NotificationController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @Operation(summary = "Send notification", description = "Send a notification to a specified token with a title and body")
    @GetMapping("/sendNotification")
    public String sendNotification(@RequestParam String token, @RequestParam String title, @RequestParam String body) {
        fcmService.sendNotification(token, title, body);
        return "Notification sent!";
    }
}