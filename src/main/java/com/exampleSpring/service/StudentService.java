package com.exampleSpring.service;

import com.exampleSpring.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    Student deleteStudent(Long id);
    List<Student> getStudentByName(String name);
}
