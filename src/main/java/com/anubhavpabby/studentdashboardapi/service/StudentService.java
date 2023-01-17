package com.anubhavpabby.studentdashboardapi.service;

import com.anubhavpabby.studentdashboardapi.dao.StudentDao;
import com.anubhavpabby.studentdashboardapi.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public Student getStudentById(Long studentId) {
        Optional<Student> studentById = studentDao.findById(studentId);

        if(studentById.isEmpty()) {
            throw new IllegalStateException("Student does not exist with this studentId " + studentId);
        }

        return studentById.get();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentDao.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("Student already exists with email " + student.getEmail());
        } else {
            studentDao.save(student);
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

    @Transactional
    public void updateStudent(Long studentId, String name, String email, LocalDate dob) {
        Optional<Student> studentById = studentDao.findById(studentId);

        if(studentById.isEmpty()) {
            throw new IllegalStateException("Student does not exist with this studentId " + studentId);
        }

        Student student = studentById.get();

        if(name != null && name.length() > 0 && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(name, student.getEmail())) {
            Optional<Student> studentByEmail = studentDao.findStudentByEmail(email);

            if(studentByEmail.isPresent()) {
                throw new IllegalStateException("Student already exists with email " + email);
            } else {
                student.setEmail(email);
            }
        }

        if(dob != null && !Objects.equals(dob, student.getDob())) {
            student.setDob(dob);
        }
    }

    public void deleteAllStudents() {
        studentDao.deleteAll();
    }

    public void deleteStudentById(Long studentId) {
        boolean doesStudentExists = studentDao.existsById(studentId);

        if (!doesStudentExists) {
            throw new IllegalStateException("Cannot find student with this studentId " + studentId);
        } else {
            studentDao.deleteById(studentId);
        }
    }
}
