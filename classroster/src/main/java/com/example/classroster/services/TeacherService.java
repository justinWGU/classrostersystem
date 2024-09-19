package com.example.classroster.services;

import com.example.classroster.entity.Student;
import com.example.classroster.entity.Teacher;
import com.example.classroster.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteAllTeachers() {
        teacherRepository.deleteAll();
    }

}
