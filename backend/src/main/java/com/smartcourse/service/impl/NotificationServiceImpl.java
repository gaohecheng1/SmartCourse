package com.smartcourse.service.impl;

import com.smartcourse.entity.Notification;
import com.smartcourse.repository.NotificationRepository;
import com.smartcourse.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 通知服务实现类
 * 实现通知相关的业务逻辑
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.orElse(null);
    }

    @Override
    public List<Notification> getNotificationsByClass(String classId) {
        return notificationRepository.findByTargetClassContaining(classId);
    }

    @Override
    public List<Notification> getNotificationsByStudent(String studentId) {
        return notificationRepository.findByTargetStudentsContaining(studentId);
    }

    @Override
    public List<Notification> getNotificationsByType(String notificationType) {
        return notificationRepository.findByNotificationType(notificationType);
    }

    @Override
    public List<Notification> getNotificationsByImportanceLevel(String importanceLevel) {
        return notificationRepository.findByImportanceLevel(importanceLevel);
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long id, Notification notification) {
        if (notificationRepository.existsById(id)) {
            notification.setId(id);
            return notificationRepository.save(notification);
        }
        return null;
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}