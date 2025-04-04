package com.oneluffy.mapper;

import com.oneluffy.entity.User;

import java.util.List;

public interface UserMapper {
    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    List<User> getAllUsers();
}
