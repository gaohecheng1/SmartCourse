package com.smartcourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 课程实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 课程名称

    @Column(nullable = false)
    private String teacher; // 授课教师

    @Column(nullable = false)
    private String location; // 上课地点

    @Column(name = "class_id")
    private String classId; // 班级ID

    @Column(name = "weekday")
    private Integer weekday; // 周几（1-7，对应周一到周日）

    @Column(name = "start_time")
    private String startTime; // 开始时间

    @Column(name = "end_time")
    private String endTime; // 结束时间

    @Column(name = "start_week")
    private Integer startWeek; // 开始周次

    @Column(name = "end_week")
    private Integer endWeek; // 结束周次

    @Column(name = "course_type")
    private String courseType; // 课程类型（必修/选修）

    @Column(name = "credit")
    private Float credit; // 学分

    @Column(name = "created_by")
    private Long createdBy; // 创建者ID（导员）

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