package com.revature.controller;

import com.revature.model.Doctor;
import com.revature.model.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    private final UserService userService;

    @Autowired
    public DoctorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/newDoctor")
    public String newDoctorForm(Model model){
        User user = new User();
        Doctor doctor = new Doctor();
        model.addAttribute("user", user);
        model.addAttribute("doctor", doctor);
        return "doctor/new_doctor";
    }

    @PostMapping("/newDoctor")
    public String createNewDoctor(@ModelAttribute("user") User user, @ModelAttribute("doctor") Doctor doctor){
        userService.createDoctor(user, doctor);
        return "register_success";
    }
}
