package com.jobportal.job.repository;

import com.jobportal.job.model.JobApplicationMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationMapperRepository extends JpaRepository<JobApplicationMapper, Long> {
}
