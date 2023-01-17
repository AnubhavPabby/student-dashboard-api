package com.anubhavpabby.studentdashboardapi.controller;

import com.anubhavpabby.studentdashboardapi.model.Student;
import com.anubhavpabby.studentdashboardapi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get the list of all the students that exists in db.")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

//    Getting student by studentId
//    studentId is passed here as request param variable
//    @Operation(summary = "Get the requested student's data from db by using it's studentId")
//    @GetMapping(path="/get-student-by-id")
//    public Student getStudentById(@RequestParam("studentId") Long studentId) {
//        return studentService.getStudentById(studentId);
//    }

//    Getting student by studentId
//    studentId is passed here as path variable
    @Operation(summary = "Get the requested student's data from db by using it's studentId.")
    @GetMapping(path="{studentId}")
    public Student getStudentById(@Parameter(description = "Id of the student that needs to be searched.") @PathVariable("studentId") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @Operation(summary = "Create a new student in db and assigning it a unique studentId.")
    @PostMapping
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @Operation(summary = "Updating the requested student's data in db by using it's studentId.")
    @PutMapping(path="{studentId}")
    public void updateStudent(@Parameter(description = "Id of the student that needs to be updated.") @PathVariable("studentId") Long studentId,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "email", required = false) String email,
                              @RequestParam(value = "dob", required = false) LocalDate dob) {
        studentService.updateStudent(studentId, name, email, dob);
    }

    @Operation(summary = "Delete all the students that exists in db.")
    @DeleteMapping
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

//    Deleting student by studentId
//    studentId is passed here as request param variable
//    @Operation(summary = "Delete the requested student's data from db by using it's studentId")
//    @DeleteMapping(path="/get-student-by-id")
//    public void deleteStudentById(@RequestParam("studentId") Long studentId) {
//        studentService.deleteStudentById(studentId);
//    }

//    Deleting student by studentId
//    studentId is passed here as path variable
    @Operation(summary = "Delete the requested student's data from db by using it's studentId.")
    @DeleteMapping(path="{studentId}")
    public void deleteStudentById(@Parameter(description = "Id of the student that needs to be deleted.") @PathVariable("studentId") Long studentId) {
        studentService.deleteStudentById(studentId);
    }
}
