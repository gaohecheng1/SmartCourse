package com.smartcourse.service;

import com.smartcourse.entity.User;

import java.util.List;

/**
 * 用户服务接口
 * 定义用户相关的业务逻辑
 */
public interface UserService {

    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户对象
     */
    User getUserById(Long id);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUsername(String username);

    /**
     * 根据邮箱获取用户
     * @param email 邮箱
     * @return 用户对象
     */
    User getUserByEmail(String email);

    /**
     * 根据学号获取用户
     * @param studentId 学号
     * @return 用户对象
     */
    User getUserByStudentId(String studentId);

    /**
     * 根据教师编号获取用户
     * @param teacherId 教师编号
     * @return 用户对象
     */
    User getUserByTeacherId(String teacherId);

    /**
     * 创建新用户（注册）
     * @param user 用户对象
     * @return 创建后的用户对象
     */
    User createUser(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户对象，失败返回null
     */
    User login(String username, String password);

    /**
     * 更新用户信息
     * @param id 用户ID
     * @param user 更新后的用户对象
     * @return 更新后的用户对象
     */
    User updateUser(Long id, User user);

    /**
     * 删除用户
     * @param id 用户ID
     */
    void deleteUser(Long id);
}