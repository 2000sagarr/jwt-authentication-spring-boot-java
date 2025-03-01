package com.sagar.taskmanagement.controller;

import com.sagar.taskmanagement.model.Users;
import com.sagar.taskmanagement.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        return this.userService.register(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return this.userService.verify(user);
    }
}
