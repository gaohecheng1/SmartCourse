package com.smartcourse.controller;

import com.smartcourse.entity.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通知控制器
 * 处理通知相关的HTTP请求
 */
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        // TODO: 实现获取所有通知的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        // TODO: 实现根据ID获取通知的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Notification>> getNotificationsByClass(@PathVariable String classId) {
        // TODO: 实现根据班级ID获取通知的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Notification>> getNotificationsByStudent(@PathVariable String studentId) {
        // TODO: 实现根据学生ID获取通知的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{notificationType}")
    public ResponseEntity<List<Notification>> getNotificationsByType(@PathVariable String notificationType) {
        // TODO: 实现根据通知类型获取通知的逻辑
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        // TODO: 实现创建通知的逻辑
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        // TODO: 实现更新通知的逻辑
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        // TODO: 实现删除通知的逻辑
        return ResponseEntity.ok().build();
    }
}