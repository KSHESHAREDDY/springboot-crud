package com.app.personal.controller;

import com.app.personal.model.UserInfo;
import com.app.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }
}
