package com.smartcourse.service;

import com.smartcourse.entity.Notification;

import java.util.List;

/**
 * 通知服务接口
 * 定义通知相关的业务逻辑
 */
public interface NotificationService {

    /**
     * 获取所有通知
     * @return 通知列表
     */
    List<Notification> getAllNotifications();

    /**
     * 根据ID获取通知
     * @param id 通知ID
     * @return 通知对象
     */
    Notification getNotificationById(Long id);

    /**
     * 根据班级ID获取通知列表
     * @param classId 班级ID
     * @return 通知列表
     */
    List<Notification> getNotificationsByClass(String classId);

    /**
     * 根据学生ID获取通知列表
     * @param studentId 学生ID
     * @return 通知列表
     */
    List<Notification> getNotificationsByStudent(String studentId);

    /**
     * 根据通知类型获取通知列表
     * @param notificationType 通知类型
     * @return 通知列表
     */
    List<Notification> getNotificationsByType(String notificationType);

    /**
     * 根据重要程度获取通知列表
     * @param importanceLevel 重要程度
     * @return 通知列表
     */
    List<Notification> getNotificationsByImportanceLevel(String importanceLevel);

    /**
     * 创建新通知
     * @param notification 通知对象
     * @return 创建后的通知对象
     */
    Notification createNotification(Notification notification);

    /**
     * 更新通知信息
     * @param id 通知ID
     * @param notification 更新后的通知对象
     * @return 更新后的通知对象
     */
    Notification updateNotification(Long id, Notification notification);

    /**
     * 删除通知
     * @param id 通知ID
     */
    void deleteNotification(Long id);
}