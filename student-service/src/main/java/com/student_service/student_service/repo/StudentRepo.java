package com.student_service.student_service.repo;

import com.student_service.student_service.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID> {
}
