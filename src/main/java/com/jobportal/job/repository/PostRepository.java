package com.jobportal.job.repository;

import com.jobportal.job.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByUuid(String uuid);
}
