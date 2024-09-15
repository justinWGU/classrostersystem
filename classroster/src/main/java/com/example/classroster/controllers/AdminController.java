package com.example.classroster.controllers;

import com.example.classroster.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showTeachers(Model model) {

        // create teacher object to pass to html page
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teacher-admin";
    }

    // displays student page for admin related functions
    @GetMapping("/students")
    public String showStudents() {

        return "student-admin";
    }

    // displays course page for admin related functions
    @GetMapping("/courses")
    public String showCourses() {

        return "course-admin";
    }
}
