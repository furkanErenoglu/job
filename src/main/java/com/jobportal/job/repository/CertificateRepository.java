package com.jobportal.job.repository;

import com.jobportal.job.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long>{
    List<Certificate> findByProfileId(long profileId);
}
