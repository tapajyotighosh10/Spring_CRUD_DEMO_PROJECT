package com.exampleSpring.service.Impl;

import com.exampleSpring.entity.Student;
import com.exampleSpring.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceImplTest {

    private Student student;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeAll
    static void setupAll() {
        System.out.println("Starting test execution...");
    }

    @BeforeEach
    public void setUp() {
        student = Student.builder()
                .studentId(1L)
                .studentName("test")
                .claas("xx")
                .mobileNumber("123456789")
                .email("test@gmail.com")
                .build();
    }

    @Test
    @Order(1)
    public void testCreateStudent() {
        //preconditions
        given(studentRepository.save(student)).willReturn(student);

        //action
        Student savedStudent = studentService.createStudent(student);

        //assertions
        System.out.println(savedStudent);
       assertThat(savedStudent).isNotNull().isEqualTo(student);
       verify(studentRepository,times(1)).save(student);
    }
    @Test
    @Order(2)
    public void testGetStudentById() {
        //preconditions
        given(studentRepository.findById(1L)).willReturn(Optional.of(student));

        //action
        Student studentById= studentService.getStudentById(student.getStudentId());

        //assertions
        System.out.println(studentById);
       assertThat(studentById).isNotNull();
    }

    @Test
    @Order(3)
    public void testGetAllStudents() {
      Student student2 = Student.builder()
                .studentId(2L)
                .studentName("test2")
                .claas("xy")
                .mobileNumber("5459222116")
                .email("test2@gmail.com")
                .build();

        //preconditions
        given(studentRepository.findAll()).willReturn(List.of(student,student2));

        //action
        List<Student> studentsList = studentService.getAllStudents();

        //assertions
        System.out.println(studentsList);
        assertThat(studentsList).isNotNull();
        assertThat(studentsList.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    public void updateStudent(){
        given(studentRepository.findById(student.getStudentId())).willReturn(Optional.of(student));

        student.setStudentName("Tapajyoti");
        student.setEmail("tapa@gmail.com");

        Student updatedStudent = studentService.updateStudent(student.getStudentId(), student);
        System.out.println(updatedStudent);
        assertThat(student).isNotNull();
        assertThat(updatedStudent.getStudentName()).isEqualTo("Tapajyoti");
        assertThat(updatedStudent.getEmail()).isEqualTo("tapa@gmail.com");
    }

    @Test
    @Order(5)
    public void deleteStudent(){
        // Given: Mock findById() to return a student before deletion
        given(studentRepository.findById(student.getStudentId())).willReturn(Optional.of(student));
        //preconditions
        willDoNothing().given(studentRepository).deleteById(student.getStudentId());
        //action
        studentService.deleteStudent(student.getStudentId());

        //verify
        verify(studentRepository,times(1)).deleteById(student.getStudentId());

    }
    @AfterEach
    void tearDown() {
        System.out.println("Test completed. Cleaning up...");
        student = null;
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests completed!");
    }


}