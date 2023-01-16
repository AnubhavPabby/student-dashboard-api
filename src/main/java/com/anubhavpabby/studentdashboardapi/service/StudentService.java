package com.anubhavpabby.studentdashboardapi.service;

import com.anubhavpabby.studentdashboardapi.model.Student;
import com.anubhavpabby.studentdashboardapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByEmail(String email) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);

        if(studentByEmail.isEmpty()) {
            throw new IllegalStateException("Student does not exist with email " + email);
        }

        return studentByEmail.get();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("Student already exists with email " + student.getEmail());
        } else {
            studentRepository.save(student);
        }
    }

//    public void addNewStudent(String email, String name, LocalDate dob) {
//        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
//
//        if(studentByEmail.isPresent()) {
//            throw new IllegalStateException("Student already exists with email " + email);
//        } else {
//            studentRepository.save(new Student(name, email, dob));
//        }
//    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    public void deleteStudentByEmail(String email) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);

        if(studentByEmail.isEmpty()) {
            throw new IllegalStateException("Cannot find student with the email " + email);
        } else {
            studentRepository.delete(studentByEmail.get());
        }
    }
}
