package com.example.classroster.controllers;

import com.example.classroster.entity.Student;
import com.example.classroster.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Controller for showing the list of students and displaying link to add new one
    @GetMapping("/students")
    public String listStudents(Model model) {

        // get list of students from the database
        List<Student> students = studentService.getAllStudents();

        // add them to the model to pass them to front-end
        model.addAttribute("students", students);

        return "student-list";

    }

    // Controller that displays form to add new stud
    @GetMapping("/students/new")
    public String showNewStudForm(Model model) {

        // create and add a new stud to the model
        model.addAttribute("student", new Student());

        return "student-form";
    }

    @PostMapping("students")
    public String addStudent(@ModelAttribute Student student) { // new Student object doesn't have to be created in a post method because the get method alreayd passed the obj.
        studentService.saveStudent(student);
        return "redirect:/students";
    }
}
