package com.smartcourse.service.impl;

import com.smartcourse.entity.Course;
import com.smartcourse.repository.CourseRepository;
import com.smartcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 课程服务实现类
 * 实现课程相关的业务逻辑
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
    }

    @Override
    public List<Course> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacher(teacherId);
    }

    @Override
    public List<Course> getCoursesByClassroom(String classroom) {
        return courseRepository.findByLocation(classroom);
    }

    @Override
    public List<Course> getCoursesByTimeSlot(String timeSlot) {
        // 这里需要根据具体的时间段格式进行查询
        // 可能需要解析timeSlot，然后根据weekday、startTime和endTime进行查询
        return courseRepository.findByStartTimeContaining(timeSlot);
    }

    @Override
    public List<Course> getCoursesByClassId(String classId) {
        return courseRepository.findByClassId(classId);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        if (courseRepository.existsById(id)) {
            course.setId(id);
            return courseRepository.save(course);
        }
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}