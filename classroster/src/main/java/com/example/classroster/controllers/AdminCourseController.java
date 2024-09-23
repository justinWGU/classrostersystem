package com.example.classroster.controllers;

import com.example.classroster.entity.Course;
import com.example.classroster.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// displays course page for admin related functions
@Controller
@RequestMapping("/admin")
public class AdminCourseController {

    private final CourseService courseService;

    @Autowired
    public AdminCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Admin Course Home page
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

    // Searches for Course. If found: returns course-detail page. Else: return to Get controller with error messg
    @PostMapping("/course-search")
    public String courseSearch(@ModelAttribute Course searchCourse, RedirectAttributes redirectAttributes) {

        // check for course existence using the user provided id
        if (!(courseService.getCourse(searchCourse.getId()) == null)) {

            // if course exists, redirect to course-details page
            return "redirect:/admin/courses/" + searchCourse.getId();

        } else { // else return to searchCourse page with error message

            // add error message
            redirectAttributes.addFlashAttribute("errorMessage", "No course with the given id exists.");
            return "redirect:/admin/courses";

            }
    }
    // display course details page based on the id param
    @GetMapping("/courses/{id}")
    public String courseDetails(@PathVariable Long id, Model model) { // get parameter from URL based on its name
        
        model.addAttribute("course", courseService.getCourse(id));
        
        return "course-details";
    }

    // Add Course
    @GetMapping("/course-add")
    public String courseAdd(Model model) {

        // creates new Course obj so user can fill out form
        model.addAttribute("course", new Course());

        return "course-add";
    }

    // adds course created by user data, and redirects to course add page
    @PostMapping("/course-add")
    public String courseAdd(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {

        // add course to DB
        courseService.saveCourse(new Course(course.getName()));

        redirectAttributes.addFlashAttribute("successMessage", "Successfully added " + course.getName() + " as a new course!");

        return "redirect:/admin/course-add";
    }    
}
