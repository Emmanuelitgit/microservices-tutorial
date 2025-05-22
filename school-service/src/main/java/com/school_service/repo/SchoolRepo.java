package com.school_service.repo;

import com.school_service.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchoolRepo extends JpaRepository<School, UUID> {
}
