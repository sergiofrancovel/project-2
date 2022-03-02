package com.revature.controller;

import com.revature.model.Pharmacist;
import com.revature.model.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PharmacistController {

    private final UserService userService;

    @Autowired
    public PharmacistController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/newPharmacist")
    public String newPharmacistForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "pharmacist/new_pharmacist";
    }

    @PostMapping("/newPharmacist")
    public String createNewPharmacist(@ModelAttribute("user") User user, Pharmacist pharmacist){
        userService.createPharmacist(user, pharmacist);
        return "register_success";
    }
}
