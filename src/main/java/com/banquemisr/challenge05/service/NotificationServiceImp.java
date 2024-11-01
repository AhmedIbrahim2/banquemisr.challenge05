package com.banquemisr.challenge05.service;

import com.banquemisr.challenge05.model.Notification;
import com.banquemisr.challenge05.model.User;
import com.banquemisr.challenge05.repository.NotificationRepository;
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

        emailService.sendEmail(user.getEmail(), " Task Notification", message);

        return "Notification Saved Successfully";
    }



}
