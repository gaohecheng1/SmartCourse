package com.smartcourse.service.impl;

import com.smartcourse.entity.User;
import com.smartcourse.repository.UserRepository;
import com.smartcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户服务实现类
 * 实现用户相关的业务逻辑
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId);
    }

    @Override
    public User getUserByTeacherId(String teacherId) {
        return userRepository.findByTeacherId(teacherId);
    }

    @Override
    public User createUser(User user) {
        // 检查用户名和邮箱是否已存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("邮箱已存在");
        }
        
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            // 如果更新密码，需要加密
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                // 如果没有提供新密码，保留原密码
                User existingUser = userRepository.findById(id).orElse(null);
                if (existingUser != null) {
                    user.setPassword(existingUser.getPassword());
                }
            }
            
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}