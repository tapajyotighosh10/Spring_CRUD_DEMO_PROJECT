package com.exampleSpring.service.Impl;

import com.exampleSpring.entity.Student;
import com.exampleSpring.repository.StudentRepository;
import com.exampleSpring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with this student id"));
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student std = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with this student id"));
        std.setStudentName(student.getStudentName());
        std.setClaas(student.getClaas());
        std.setMobileNumber(student.getMobileNumber());
        std.setEmail(student.getEmail());
        return std;
    }

    @Override
    public Student deleteStudent(Long id) {
        Student std = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with this student id"));
        studentRepository.deleteById(id);
        return std;
    }

    @Override
    public Optional<Student> getStudentByName(String studentName) {
        return studentRepository.getStudentsByName(studentName);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student createOrUpdateStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());

        if (existingStudent.isPresent()) {
            Student std = existingStudent.get(); // if found then update the existing data otherwise save the new data
            std.setStudentName(student.getStudentName());
            std.setClaas(student.getClaas());
            std.setMobileNumber(student.getMobileNumber());
            return studentRepository.save(std);
        } else {
            return studentRepository.save(student);
        }
    }

    @Override
    public List<Student> getStudentWithSorting(String field){
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<Student> getStudentWithPagination(int offset,int pageSize){
        Page<Student> studentPage= studentRepository.findAll(PageRequest.of(offset, pageSize));
        return studentPage;
    }

    @Override
    public Page<Student> getStudentWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Student> studentPage= studentRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return studentPage;
    }


}
