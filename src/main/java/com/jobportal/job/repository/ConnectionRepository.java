package com.jobportal.job.repository;

import com.jobportal.job.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    Connection findByUuid(String uuid); 
}
