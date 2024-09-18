package com.example.classroster.bootstrap;
import com.example.classroster.entity.Course;
import com.example.classroster.entity.Student;
import com.example.classroster.entity.Teacher;
import com.example.classroster.services.CourseService;
import com.example.classroster.services.StudentService;
import com.example.classroster.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired // DI student service to save students as intial data for DB
    public BootStrap(StudentService studentService, TeacherService teacherService, CourseService courseService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @Override // method that runs when app starts to create sample data
    public void run(String... args) throws Exception {

        // reset database
        courseService.deleteAllCourses();
        teacherService.deleteAllTeachers();
        studentService.deleteAllStudents();

        // create new courses and add them to DBs
        courseService.saveCourse(new Course("Course1"));
        courseService.saveCourse(new Course("Course2"));
        courseService.saveCourse(new Course("Course3"));

        // create new teachers and add them to DBs
        teacherService.saveTeacher(new Teacher("Teacher1"));
        teacherService.saveTeacher(new Teacher("Teacher2"));
        teacherService.saveTeacher(new Teacher("Teacher3"));

        // create new studs and add them to DBs
        studentService.saveStudent(new Student("Justin"));
        studentService.saveStudent(new Student("Osckie"));
        studentService.saveStudent(new Student("Elijah"));

    }
}
