package com.example.banquemisr.service;

import com.example.banquemisr.model.Notification;
import com.example.banquemisr.model.User;
import com.example.banquemisr.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImp implements NotificationService {


    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public String createNotification(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);

        emailService.sendEmail(user.getEmail(), "Task Notification", message);

        return "Notification Saved Successfully";
    }



}
