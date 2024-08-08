package com.jobportal.job.service;

public interface CommentService {
    String createComment(CommentDto commentDto);

    String deleteComment(Long id, String userUuid);

    String updateComment(Long commentId, CommentDto commentDto);

    Page<CommentDto> getAllComments(int pageNo, int pageSize);

    CommentDto getCommentById(Long id);
}
