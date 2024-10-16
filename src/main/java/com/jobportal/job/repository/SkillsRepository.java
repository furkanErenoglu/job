package com.jobportal.job.repository;

import com.jobportal.job.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    List<Skills> findByNameContainingIgnoreCase(String name);
}
