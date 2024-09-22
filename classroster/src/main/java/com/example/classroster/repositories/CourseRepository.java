package com.example.classroster.repositories;
import com.example.classroster.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // JPA uses naming convention to create a custom query to find teacher fields that are null
    List<Course> findByTeacherIsNull();
}
