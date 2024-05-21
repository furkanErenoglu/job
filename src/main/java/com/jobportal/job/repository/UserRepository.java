package com.jobportal.job.repository;

import com.jobportal.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUuid(String uuid);
}
