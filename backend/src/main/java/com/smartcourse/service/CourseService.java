package com.smartcourse.service;

import com.smartcourse.entity.Course;

import java.util.List;

/**
 * 课程服务接口
 * 定义课程相关的业务逻辑
 */
public interface CourseService {

    /**
     * 获取所有课程
     * @return 课程列表
     */
    List<Course> getAllCourses();

    /**
     * 根据ID获取课程
     * @param id 课程ID
     * @return 课程对象
     */
    Course getCourseById(Long id);

    /**
     * 根据教师ID获取课程列表
     * @param teacherId 教师ID
     * @return 课程列表
     */
    List<Course> getCoursesByTeacher(Long teacherId);

    /**
     * 根据教室获取课程列表
     * @param classroom 教室名称
     * @return 课程列表
     */
    List<Course> getCoursesByClassroom(String classroom);

    /**
     * 根据时间段获取课程列表
     * @param timeSlot 时间段
     * @return 课程列表
     */
    List<Course> getCoursesByTimeSlot(String timeSlot);

    /**
     * 根据班级ID获取课程列表
     * @param classId 班级ID
     * @return 课程列表
     */
    List<Course> getCoursesByClassId(String classId);

    /**
     * 创建新课程
     * @param course 课程对象
     * @return 创建后的课程对象
     */
    Course createCourse(Course course);

    /**
     * 更新课程信息
     * @param id 课程ID
     * @param course 更新后的课程对象
     * @return 更新后的课程对象
     */
    Course updateCourse(Long id, Course course);

    /**
     * 删除课程
     * @param id 课程ID
     */
    void deleteCourse(Long id);
}