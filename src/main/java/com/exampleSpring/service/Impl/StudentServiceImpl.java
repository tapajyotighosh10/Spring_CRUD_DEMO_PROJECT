package com.exampleSpring.service.Impl;


import com.exampleSpring.entity.Student;
import com.exampleSpring.repository.StudentRepository;
import com.exampleSpring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found with this student id"));
    }

    @Override
    public Student updateStudent(Long id, Student student) {

    Student std = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found with this student id"));
    std.setStudentName(student.getStudentName());
    std.setClaas(student.getClaas());
    std.setMobileNumber(student.getMobileNumber());
    std.setEmail(student.getEmail());
    return std;
    }

    @Override
    public Student deleteStudent(Long id) {
        Student std = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found with this student id"));
        studentRepository.deleteById(id);
        return std;
    }

    @Override
    public List<Student> getStudentByName(String studentName) {
        return studentRepository.getStudentsByName(studentName);
    }
}
