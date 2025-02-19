package com.exampleSpring.repository;

import com.exampleSpring.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "SELECT * FROM student WHERE student_name = ?1", nativeQuery = true)
    List<Student> getStudentsByName(String studentName);
}
