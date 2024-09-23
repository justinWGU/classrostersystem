package com.example.classroster.controllers;

import com.example.classroster.entity.Course;
import com.example.classroster.entity.Teacher;
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

    // controller to update course data
    @GetMapping("/course-update")
    public String updateCourse(@RequestParam Long id, Model model) { // get input tag with the specified name

        // use id param to search for course and pass to front end
        model.addAttribute("course", courseService.getCourse(id));

        return "course-update";
    }

    // update course information
    @PostMapping("/course-update")
    public String updateCourse(@ModelAttribute Course course) {

        // update existing course with updated courses data
        Course currentCourse = courseService.getCourse(course.getId());
        currentCourse.setName(course.getName());
        courseService.saveCourse(currentCourse);

        return "redirect:/admin/course-update-success/" + currentCourse.getId();
    }

    // display updated course success page
    @GetMapping("/course-update-success/{id}")
    public String courseUpdateSuccess(@PathVariable Long id, Model model) {

        model.addAttribute("course", courseService.getCourse(id));

        return "course-update-success";
    }

    // Delete Course
    @PostMapping("/course-delete")
    public String courseDelete(@RequestParam Long id, RedirectAttributes redirectAttributes) { // get id from FE

        // capture Course name for success msg
        String courseName = courseService.getCourse(id).getName();

        // search using id & delete from DB
        courseService.deleteCourse(courseService.getCourse(id));

        // Delete Course success message
        redirectAttributes.addFlashAttribute("successMessage", courseName + " Course successfully deleted!");

        // redirect to GetMap to course admin page
        return "redirect:/admin/courses";
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

}
