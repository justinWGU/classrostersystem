package com.example.classroster.bootstrap;
import com.example.classroster.entity.Student;
import com.example.classroster.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    private StudentService studentService;

    @Autowired // DI student service to save students as intial data for DB
    public BootStrap(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override // method that runs when app starts to create sample data
    public void run(String... args) throws Exception {

        // create new studs and add them to DBs
        studentService.saveStudent(new Student("Justin"));
        studentService.saveStudent(new Student("Osckie"));
        studentService.saveStudent(new Student("Elijah"));

    }
}
