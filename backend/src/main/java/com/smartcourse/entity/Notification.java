package com.smartcourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 通知实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 通知标题

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 通知内容

    @Column(name = "notification_type")
    private String notificationType; // 通知类型：notice（通知）、homework（作业）、activity（活动）、other（其他）

    @Column(name = "importance_level")
    private String importanceLevel; // 重要程度：low（普通）、medium（重要）、high（非常重要）、urgent（紧急）

    @Column(name = "created_by")
    private Long createdBy; // 创建者ID（导员）

    @Column(name = "target_class")
    private String targetClass; // 目标班级ID，多个班级用逗号分隔

    @Column(name = "target_students")
    private String targetStudents; // 目标学生ID，多个学生用逗号分隔

    @Column(name = "publish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime; // 发布时间

    @Column(name = "expire_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime; // 过期时间

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}