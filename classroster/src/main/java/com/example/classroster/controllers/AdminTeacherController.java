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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminTeacherController {
    private final StudentService studentService;   // all of the admin functions will be managed by this controller
    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public AdminTeacherController(TeacherService teacherService, StudentService studentService, CourseService courseService) {
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

    // Check for teacher existence in database and return to front end
    @PostMapping("/teacher-search")
    public String teacherSearch(@ModelAttribute Teacher searchTeacher, RedirectAttributes redirectAttributes) {

        // check for teacher existence using the user provided id
        if (!(teacherService.getTeacher(searchTeacher.getId()) == null)) {

            // if teacher exists, redirect to teacher-details page
            return "redirect:/admin/teachers/" + searchTeacher.getId();

        } else { // else return to searchTeacher page with error message

            // add error message
            redirectAttributes.addFlashAttribute("errorMessage", "No teacher with the given id exists.");
            return "redirect:/admin/teachers";

        }
    }

    // Add Teacher
    @GetMapping("/teacher-add")
    public String teacherAdd(Model model) {

        // creates new teacher obj so user can fill out form
        model.addAttribute("teacher", new Teacher());
        return "teacher-add";
    }

    // adds teacher created by user data, and redirects to teacher add page
    @PostMapping("/teacher-add")
    public String teacherAdd(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {

        // add teacher to DB
        teacherService.saveTeacher(new Teacher(teacher.getName()));

        redirectAttributes.addFlashAttribute("successMessage", "Successfully added " + teacher.getName() + " as a new teacher!");

        return "redirect:/admin/teacher-add";
    }

    // controller to update teacher data
    @GetMapping("/teacher-update")
    public String updateTeacher(@RequestParam Long id, Model model) { // get input tag with the specified name

        // use id param to search for teacher and pass to front end
        model.addAttribute("teacher", teacherService.getTeacher(id));

        return "teacher-update";
    }

    // update teacher information
    @PostMapping("/teacher-update")
    public String updateTeacher(@ModelAttribute Teacher teacher) {

        // update existing teacher with updated teachers data
        Teacher currentTeacher = teacherService.getTeacher(teacher.getId());
        currentTeacher.setName(teacher.getName());
        teacherService.saveTeacher(currentTeacher);

        return "redirect:/admin/teacher-update-success/" + currentTeacher.getId();
    }

    // display updated teacher success page
    @GetMapping("/teacher-update-success/{id}")
    public String teacherUpdateSuccess(@PathVariable Long id, Model model) {

        model.addAttribute("teacher", teacherService.getTeacher(id));

        return "teacher-update-success";
    }

    // display teacher details page based on the id param
    @GetMapping("/teachers/{id}")
    public String teacherDetails(@PathVariable Long id, Model model) { // get parameter from URL based on its name

        model.addAttribute("teacher", teacherService.getTeacher(id));
        return "teacher-details";
    }

    // handle post request from front end
    @PostMapping("/teacher-delete")
    public String teacherDelete(@RequestParam Long id) { // get id from FE

        // search using id & delete from DB
        teacherService.deleteTeacher(teacherService.getTeacher(id));

        // redirect to GetMap to teacher admin page
        return "redirect:/admin/teachers";
    }

    // handle assign teacher courses button
    @GetMapping("/teacher-assign")
    public String teacherAssign(@RequestParam Long id, Model model) {

        // add teacher to model
        model.addAttribute("teacher", teacherService.getTeacher(id));

        // get teacher's course list & add to model
        model.addAttribute("teacherCourseList", teacherService.getTeacher(id).getCourses());

        // get list of unassigned courses
        model.addAttribute("courseList", courseService.getCoursesWithoutTeachers());

        return "teacher-assign";
    }

    // assign teacher to specified course
    @PostMapping("/teacher-assign")
    public String teacherAssign(@RequestParam Long courseId, @RequestParam Long teacherId) {
        // retrieve teacher from DB
        Teacher teacher = teacherService.getTeacher(teacherId);

        // assign teacher to course
        Course course = courseService.getCourse(courseId);

        // save teacher and course to DB
        teacher.addCourse(course);
        teacherService.saveTeacher(teacher);

        return "redirect:/admin/teacher-assign?id=" + teacherId;
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

}
