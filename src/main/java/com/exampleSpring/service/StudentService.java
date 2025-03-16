package com.exampleSpring.service;

import com.exampleSpring.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    Student deleteStudent(Long id);
    Optional<Student> getStudentByName(String name);
    // upsert code
    Optional<Student> findByEmail(String email);

    Student createOrUpdateStudent(Student student);

    List<Student> getStudentWithSorting(String field);

    Page<Student> getStudentWithPagination(int offset, int pageSize);

    Page<Student> getStudentWithPaginationAndSorting(int offset,int pageSize,String field);

    }
