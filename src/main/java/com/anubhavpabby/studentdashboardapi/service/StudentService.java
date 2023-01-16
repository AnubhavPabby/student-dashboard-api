package com.anubhavpabby.studentdashboardapi.service;

import com.anubhavpabby.studentdashboardapi.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Anubhav Pabby",
                        "anubhav.pabby19@vit.edu",
                        LocalDate.of(2000, Month.JULY, 20),
                        22
                )
        );
    }
}
