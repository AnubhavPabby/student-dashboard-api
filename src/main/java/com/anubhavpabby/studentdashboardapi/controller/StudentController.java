package com.anubhavpabby.studentdashboardapi.controller;

import com.anubhavpabby.studentdashboardapi.model.Student;
import com.anubhavpabby.studentdashboardapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
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

    @GetMapping(path="/get-student-by-email")
    public Student getStudentByEmail(@RequestParam("email") String email) {
        return studentService.getStudentByEmail(email);
    }

    @PostMapping(path="create-new-student")
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

//    @PostMapping(path="create-new-student")
//    public void addNewStudent(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("date-of-birth") LocalDate dateOfBirth) {
//        studentService.addNewStudent(email, name, dateOfBirth);
//    }

    @DeleteMapping("delete-all-students")
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

    @DeleteMapping(path="/delete-student-by-email")
    public void deleteStudentByEmail(@RequestParam("email") String email) {
        studentService.deleteStudentByEmail(email);
    }
}
