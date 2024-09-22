package com.example.classroster.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // cascade all: Whenever a teacher is saved or deleted, associated courses will do the same
    // orphanRemoval: Whenever an associated course is removed from teacher, also delete it from DB
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    // When passing already existing courses to teacher, teacher must be explicitly assigned to each course
    public Teacher(String name, List<Course> courses) {
        this.name = name;
        setCourses(courses);
    }

    // add a course to teacher's list of courses and assign teacher to that course
    public void addCourse(Course course) {
        courses.add(course);
        course.setTeacher(this);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        for (Course course: courses) {
            addCourse(course);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
