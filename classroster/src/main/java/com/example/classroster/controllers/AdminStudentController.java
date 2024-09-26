package com.example.classroster.controllers;

import com.example.classroster.entity.Course;
import com.example.classroster.entity.Student;
import com.example.classroster.services.CourseService;
import com.example.classroster.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/student")
public class AdminStudentController {

    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public AdminStudentController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    // Admin student Home page
    @GetMapping("/home")
    public String showStudents(Model model) {

        // get list of students from DB to put to display
        List<Student> students = studentService.getAllStudents(); // list might be empty
        model.addAttribute("students", students);

        // create student object to bind to search form
        Student student = new Student();
        model.addAttribute("searchStudent", student);

        return "student-admin";
    }

    // Searches for Student. If found: returns student-detail page. Else: return to Get controller with error messg
    @PostMapping("/search")
    public String studentSearch(@ModelAttribute Student searchStudent, RedirectAttributes redirectAttributes) {

        // check for student existence using the user provided id
        if (!(studentService.getStudent(searchStudent.getId()) == null)) {

            // if student exists, redirect to student-details page
            return "redirect:/admin/student/details/" + searchStudent.getId();

        } else { // else return to searchStudent page with error message

            // add error message
            redirectAttributes.addFlashAttribute("errorMessage", "No student with the given id exists.");
            return "redirect:/admin/student/home";

        }
    }

    // Return student-details page based on the id param
    @GetMapping("/details/{id}")
    public String studentDetails(@PathVariable Long id, Model model) { // get parameter from URL based on its name

        model.addAttribute("student", studentService.getStudent(id));

        return "student-details";
    }

    // Add Student
    @GetMapping("/add")
    public String studentAdd(Model model) {

        // creates new Student obj so user can fill out form
        model.addAttribute("student", new Student());

        return "student-add";
    }

    // Adds Student created by user data. Redirects to previous page - "student-add"
    @PostMapping("/student-add")
    public String studentAdd(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {

        // save student to DB
        studentService.saveStudent(new Student(student.getName()));

        redirectAttributes.addFlashAttribute("successMessage", "Successfully added " + student.getName() + " as a new student!");

        return "redirect:/admin/student/add";
    }

    // Display update Student page
    @GetMapping("/update")
    public String updateStudent(@RequestParam Long id, Model model) { // get input tag with the specified name

        // search for student in DB
        model.addAttribute("student", studentService.getStudent(id));

        return "student-update";
    }

    // Update Student information
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {

        // update existing student with updated students data
        Student currentStudent = studentService.getStudent(student.getId());
        currentStudent.setName(student.getName());
        studentService.saveStudent(currentStudent);

        return "redirect:/admin/student/update/success" + currentStudent.getId();
    }

    // display updated student success page
    @GetMapping("/update/success/{id}")
    public String studentUpdateSuccess(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.getStudent(id));

        return "student-update-success";
    }

    // Delete Student
    @PostMapping("/delete")
    public String studentDelete(@RequestParam Long id, RedirectAttributes redirectAttributes) { // get id from FE

        // capture Student name for success msg
        String studentName = studentService.getStudent(id).getName();

        // search using id & delete from DB
        studentService.deleteStudent(studentService.getStudent(id));

        // Delete Student success message
        redirectAttributes.addFlashAttribute("successMessage", studentName + " Student successfully deleted!");

        // redirect to admin student home page
        return "redirect:/home";
    }

    // Assign button
    @GetMapping("/assign")
    public String studentAssign(@RequestParam Long id, Model model) {

        // get Student from DB using param id
        model.addAttribute("student", studentService.getStudent(id));

        // get list of courses from DB
        model.addAttribute("courseList", courseService.getAllCourses());

        return "student-assign";
    }

    // Enroll student in course
    @PostMapping("/assign")
    public String studentAssign(@RequestParam Long studentId, @RequestParam Long courseId, RedirectAttributes redirectAttributes) {

        // get student from DB
        Student student = studentService.getStudent(studentId);

        // get course from DB
        Course course = courseService.getCourse(courseId);

        // assign course to stud & assign stud to course
        student.addCourse(course);
        course.addStudents(student);

        // persist stud & course
        studentService.saveStudent(student);

        // redirect to assign page w/ succ msg
        redirectAttributes.addFlashAttribute("successMessage", "Assigned " + course.getName() + " to " + student.getName() + "!");

        return "redirect:/admin/student/assign?id=" + studentId;
    }
}
