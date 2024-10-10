package com.jobportal.job.controllers;

import com.jobportal.job.dtos.CommentDto;
import com.jobportal.job.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @DeleteMapping("/{commentId}/user/{userUuid}")
    public String deleteComment(@PathVariable Long commentId, @PathVariable String userUuid) {
        return commentService.deleteComment(commentId, userUuid);
    }

    @PutMapping("/{commentId}")
    public String updateComment(@PathVariable long commentId,@RequestBody CommentDto commentDto) {
        return commentService.updateComment(commentId,commentDto);
    }

    @GetMapping("/{commentId}")
    public CommentDto getComment(@PathVariable long commentId) {
        return commentService.getCommentById(commentId);
    }
    
}
