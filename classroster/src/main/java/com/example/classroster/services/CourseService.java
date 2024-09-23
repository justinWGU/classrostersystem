package com.example.classroster.services;
import com.example.classroster.entity.Course;
import com.example.classroster.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }

    public List<Course> getCoursesWithoutTeachers() {

        return courseRepository.findByTeacherIsNull();
      }

      public void deleteCourse(Course course) {
        courseRepository.delete(course);
      }
}
