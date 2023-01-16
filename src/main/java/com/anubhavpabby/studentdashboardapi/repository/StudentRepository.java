package com.anubhavpabby.studentdashboardapi.repository;

import com.anubhavpabby.studentdashboardapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);

//    @Query(value = "DELETE s FROM Student s WHERE s.email=?1")
//    void deleteStudentByEmail(String email);
}
