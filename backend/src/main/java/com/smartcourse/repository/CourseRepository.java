package com.smartcourse.repository;

import com.smartcourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程数据访问接口
 * 提供课程相关的数据库操作
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * 根据教师查询课程
     * @param teacher 教师名称
     * @return 课程列表
     */
    List<Course> findByTeacher(String teacher);
    
    /**
     * 根据教师ID查询课程
     * @param teacherId 教师ID
     * @return 课程列表
     */
    List<Course> findByTeacher(Long teacherId);

    /**
     * 根据上课地点查询课程
     * @param location 上课地点
     * @return 课程列表
     */
    List<Course> findByLocation(String location);

    /**
     * 根据开始时间查询课程
     * @param startTime 开始时间
     * @return 课程列表
     */
    List<Course> findByStartTime(String startTime);
    
    /**
     * 根据开始时间模糊查询课程
     * @param startTime 开始时间（部分匹配）
     * @return 课程列表
     */
    List<Course> findByStartTimeContaining(String startTime);

    /**
     * 根据班级ID查询课程
     * @param classId 班级ID
     * @return 课程列表
     */
    List<Course> findByClassId(String classId);

    /**
     * 根据周几查询课程
     * @param weekday 周几（1-7，对应周一到周日）
     * @return 课程列表
     */
    List<Course> findByWeekday(Integer weekday);

    /**
     * 根据课程类型查询课程
     * @param courseType 课程类型
     * @return 课程列表
     */
    List<Course> findByCourseType(String courseType);
    
    /**
     * 根据课程名称查询课程
     * @param name 课程名称
     * @return 课程列表
     */
    List<Course> findByName(String name);
    
    /**
     * 根据课程名称模糊查询课程
     * @param name 课程名称（部分匹配）
     * @return 课程列表
     */
    List<Course> findByNameContaining(String name);
}