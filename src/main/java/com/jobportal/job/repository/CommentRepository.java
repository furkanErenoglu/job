package com.jobportal.job.repository;

import com.jobportal.job.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByUuid(String uuid);
}
