package com.jobportal.job.repository;

import com.jobportal.job.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository< Education, Long> {
}
