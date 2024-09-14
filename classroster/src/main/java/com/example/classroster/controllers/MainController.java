package com.example.classroster.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // controller's purpose: central page that displays main function categories for admin
public class MainController {

    // controller to display main admin functions
    @GetMapping("/home")
    public String showAdmin() {

        return "home";
    }


}
