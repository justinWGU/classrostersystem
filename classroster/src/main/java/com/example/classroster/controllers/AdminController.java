package com.example.classroster.controllers;

import com.example.classroster.entity.Course;
import com.example.classroster.entity.Student;
import com.example.classroster.entity.Teacher;
import com.example.classroster.services.CourseService;
import com.example.classroster.services.StudentService;
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
public class AdminController {
    private final StudentService studentService;   // all of the admin functions will be managed by this controller
    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public AdminController (TeacherService teacherService, StudentService studentService, CourseService courseService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.courseService = courseService;
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
    public String showStudents(Model model) {

        // get list of students from DB to put to display
        List<Student> students = studentService.getAllStudents(); // list might be empty
        model.addAttribute("students", students);

        // create student object to bind to search form
        Student student = new Student();
        model.addAttribute("searchStudent", student);

        return "student-admin";
    }

    // displays course page for admin related functions
    @GetMapping("/courses")
    public String showCourses(Model model) {

        // get list of courses from DB to put to display
        List<Course> courses = courseService.getAllCourses(); // list might be empty
        model.addAttribute("courses", courses);

        // create course object to bind to search form
        Course course = new Course();
        model.addAttribute("searchCourse", course);

        return "course-admin";
    }
}
