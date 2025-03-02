package com.smartcourse.repository;

import com.smartcourse.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 通知数据访问接口
 * 提供通知相关的数据库操作
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * 根据通知类型查询通知
     * @param notificationType 通知类型
     * @return 通知列表
     */
    List<Notification> findByNotificationType(String notificationType);

    /**
     * 根据重要程度查询通知
     * @param importanceLevel 重要程度
     * @return 通知列表
     */
    List<Notification> findByImportanceLevel(String importanceLevel);

    /**
     * 根据目标班级查询通知（模糊匹配）
     * @param classId 班级ID
     * @return 通知列表
     */
    List<Notification> findByTargetClassContaining(String classId);

    /**
     * 根据目标学生查询通知（模糊匹配）
     * @param studentId 学生ID
     * @return 通知列表
     */
    List<Notification> findByTargetStudentsContaining(String studentId);

    /**
     * 根据创建者ID查询通知
     * @param createdBy 创建者ID
     * @return 通知列表
     */
    List<Notification> findByCreatedBy(Long createdBy);

    /**
     * 根据发布时间查询通知
     * @param publishTime 发布时间
     * @return 通知列表
     */
    List<Notification> findByPublishTime(Date publishTime);

    /**
     * 查询未过期的通知
     * @param currentTime 当前时间
     * @return 通知列表
     */
    List<Notification> findByExpireTimeGreaterThan(Date currentTime);

    /**
     * 根据标题模糊查询通知
     * @param title 标题（部分匹配）
     * @return 通知列表
     */
    List<Notification> findByTitleContaining(String title);
}