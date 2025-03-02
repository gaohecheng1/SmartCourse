package com.smartcourse.repository;

import com.smartcourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问接口
 * 提供用户相关的数据库操作
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户对象
     */
    User findByEmail(String email);

    /**
     * 根据学号查询用户
     * @param studentId 学号
     * @return 用户对象
     */
    User findByStudentId(String studentId);

    /**
     * 根据教师编号查询用户
     * @param teacherId 教师编号
     * @return 用户对象
     */
    User findByTeacherId(String teacherId);

    /**
     * 根据用户类型查询用户列表
     * @param userType 用户类型
     * @return 用户列表
     */
    List<User> findByUserType(String userType);

    /**
     * 根据院系查询用户列表
     * @param department 院系
     * @return 用户列表
     */
    List<User> findByDepartment(String department);

    /**
     * 根据专业查询用户列表
     * @param major 专业
     * @return 用户列表
     */
    List<User> findByMajor(String major);

    /**
     * 根据班级查询用户列表
     * @param className 班级
     * @return 用户列表
     */
    List<User> findByClassName(String className);
}