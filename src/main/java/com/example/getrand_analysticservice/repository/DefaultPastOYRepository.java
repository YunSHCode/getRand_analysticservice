package com.example.getrand_analysticservice.repository;

import com.example.getrand_analysticservice.dto.DefaultPastOY;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DefaultPastOYRepository extends JpaRepository<DefaultPastOY, Long> {
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE default_pastoneyear AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
