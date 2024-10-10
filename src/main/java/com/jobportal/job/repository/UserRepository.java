package com.jobportal.job.repository;

import com.jobportal.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findByUuid(String uuid);
    Optional<User> findByUsername(String username);
}
