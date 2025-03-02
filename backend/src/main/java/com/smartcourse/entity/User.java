package com.smartcourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    private String avatar;

    @Column(name = "user_type")
    private String userType; // 用户类型：student（学生）或 counselor（导员）

    @Column(name = "student_id")
    private String studentId; // 学号（仅学生用户）

    @Column(name = "teacher_id")
    private String teacherId; // 教师编号（仅导员用户）

    @Column(name = "department")
    private String department; // 院系

    @Column(name = "major")
    private String major; // 专业（仅学生用户）

    @Column(name = "class_name")
    private String className; // 班级（仅学生用户）

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