package com.revature.controller;

import com.revature.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/newUser")
    public String loadNewUser(){
        return "register_user";
    }
}
