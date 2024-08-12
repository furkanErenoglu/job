package com.jobportal.job.repository;

import com.jobportal.job.model.Post;
import com.jobportal.job.model.User;
import com.jobportal.job.model.UserPostCommentMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostCommentMapperRepository extends JpaRepository<UserPostCommentMapper, Long> {
    UserPostCommentMapper findByUserAndPost(User user, Post post);
}
