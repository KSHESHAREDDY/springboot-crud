package com.app.personal.controller;

import com.app.personal.model.Employee;
import com.app.personal.model.UserInfo;
import com.app.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }
}