package com.oneluffy.user.mapper;


import com.oneluffy.user.entity.User;

import java.util.List;

public interface UserMapper {
    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    List<User> getAllUsers();
}
