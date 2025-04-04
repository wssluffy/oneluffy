package com.oneluffy.controller;

import com.oneluffy.entity.User;
import com.oneluffy.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "添加用户")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新用户")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取用户")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @ApiOperation(value = "获取所有用户")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
