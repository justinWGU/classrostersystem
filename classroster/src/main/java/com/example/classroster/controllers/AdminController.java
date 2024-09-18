package com.example.classroster.controllers;

import com.example.classroster.entity.Teacher;
import com.example.classroster.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {   // all of the admin functions will be managed by this controller

    private TeacherService teacherService;

    @Autowired
    public AdminController (TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/home")
    public String adminHome() {
        return "home-admin";
    }

    // displays teacher page for admin related functs
    @GetMapping("/teachers")
    public String showTeachers(Model model) {

        // get list of teachers from DB to put to display
        List<Teacher> teachers = teacherService.getAllTeachers(); // list might be empty
        model.addAttribute("teachers", teachers);

        // create teacher object to bind to search form
        Teacher teacher = new Teacher();
        model.addAttribute("searchTeacher", teacher);
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
