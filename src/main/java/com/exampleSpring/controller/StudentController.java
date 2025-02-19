package com.exampleSpring.controller;


import com.exampleSpring.entity.Student;
import com.exampleSpring.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

private StudentService studentService;


        @PostMapping("/create")
        public ResponseEntity<Student> createStudent(@RequestBody Student student){
            Student savedStudent = studentService.createStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        }

        @GetMapping("/get_all_student")
        public ResponseEntity<List<Student>> getAllStudents(){
            List<Student> allStudents= studentService.getAllStudents();
            return new ResponseEntity<>(allStudents,HttpStatus.OK);
        }
    @GetMapping("/get_by_id")
        public ResponseEntity<Student> getStudentById(@RequestParam(name = "studentId") Long studentId){
            Student student= studentService.getStudentById(studentId);
            return new ResponseEntity<>(student,HttpStatus.OK);
        }
        @PatchMapping("/update")
        public ResponseEntity<Student> updateStudent(@RequestParam(name = "studentId") Long studentId, @RequestBody Student student){
            Student updatedStudent= studentService.updateStudent(studentId,student);
            return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
        }

        @DeleteMapping("/deleteById")
    public ResponseEntity<Student> deleteStudent(@RequestParam(name = "studentId") Long studentId){
        Student deleteStudent= studentService.deleteStudent(studentId);
        return new ResponseEntity<>(deleteStudent,HttpStatus.OK);
    }


    @GetMapping("/getByName")
    public ResponseEntity<List<Student>> getStudentByName(@RequestParam(name = "studentName") String studentName){
        List<Student> getStudent= studentService.getStudentByName(studentName);
        return new ResponseEntity<>(getStudent,HttpStatus.OK);
    }
}
