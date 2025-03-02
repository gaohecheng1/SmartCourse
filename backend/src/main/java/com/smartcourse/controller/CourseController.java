package com.smartcourse.controller;

import com.smartcourse.entity.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程控制器
 * 处理课程相关的HTTP请求
 */
@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        // TODO: 实现获取所有课程的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        // TODO: 实现根据ID获取课程的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable Long teacherId) {
        // TODO: 实现根据教师ID获取课程的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/classroom/{classroom}")
    public ResponseEntity<List<Course>> getCoursesByClassroom(@PathVariable String classroom) {
        // TODO: 实现根据教室获取课程的逻辑
        return ResponseEntity.ok().build();
    }

    @GetMapping("/time/{timeSlot}")
    public ResponseEntity<List<Course>> getCoursesByTimeSlot(@PathVariable String timeSlot) {
        // TODO: 实现根据时间段获取课程的逻辑
        return ResponseEntity.ok().build();
    }
}