package com.anubhavpabby.studentdashboardapi.controller;

import com.anubhavpabby.studentdashboardapi.model.Student;
import com.anubhavpabby.studentdashboardapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path="/get-all-students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Getting student by studentId
    // studentId is passed here as request param variable
    @GetMapping(path="/get-student-by-id")
    public Student getStudentById(@RequestParam("studentId") Long studentId) {
        return studentService.getStudentById(studentId);
    }

//    Getting student by studentId
//    studentId is passed here as path variable
//    @GetMapping(path="/get-student-by-id/{studentId}")
//    public Student getStudentById(@PathVariable("studentId") Long studentId) {
//        return studentService.getStudentById(studentId);
//    }

    @PostMapping(path="create-new-student")
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @PutMapping(path="update-student-by-id/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "email", required = false) String email,
                              @RequestParam(value = "dob", required = false) LocalDate dob) {
        studentService.updateStudent(studentId, name, email, dob);
    }

    @DeleteMapping("delete-all-students")
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

    // Deleting student by studentId
    // studentId is passed here as request param variable
    @DeleteMapping(path="/get-student-by-id")
    public void deleteStudentById(@RequestParam("studentId") Long studentId) {
        studentService.deleteStudentById(studentId);
    }

//    Deleting student by studentId
//    studentId is passed here as path variable
//    @DeleteMapping(path="/get-student-by-id/{studentId}")
//    public void deleteStudentById(@PathVariable("studentId") Long studentId) {
//        studentService.deleteStudentById(studentId);
//    }
}
