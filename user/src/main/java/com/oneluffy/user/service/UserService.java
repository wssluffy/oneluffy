package com.oneluffy.user.service;

import com.oneluffy.user.entity.User;
import com.oneluffy.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
}
