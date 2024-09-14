package com.example.classroster.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {   // all of the admin functions will be managed by this controller

    @GetMapping("/home")
    public String adminHome() {
        return "home-admin";
    }

    // displays teacher page for admin related functs
    @GetMapping("/teachers")
    public String showTeachers() {

        return "teacher-admin";
    }
}
