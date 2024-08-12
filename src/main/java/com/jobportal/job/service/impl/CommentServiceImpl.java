package com.jobportal.job.service.impl;

import com.jobportal.job.dtos.CommentDto;
import com.jobportal.job.loggers.MainLogger;
import com.jobportal.job.loggers.messages.CommentMessage;
import com.jobportal.job.model.Comment;
import com.jobportal.job.model.Post;
import com.jobportal.job.loggers.messages.UserMessage;
import com.jobportal.job.model.User;
import com.jobportal.job.model.UserPostCommentMapper;
import com.jobportal.job.repository.CommentRepository;
import com.jobportal.job.repository.PostRepository;
import com.jobportal.job.repository.UserPostCommentMapperRepository;
import com.jobportal.job.repository.UserRepository;
import com.jobportal.job.service.CommentService;
import jakarta.transaction.Transactional;
import org.hibernate.mapping.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserPostCommentMapperRepository userPostCommentMapperRepository;
    private final static MainLogger LOGGER = new MainLogger(CommentServiceImpl.class);

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,
                              UserRepository userRepository, UserPostCommentMapperRepository userPostCommentMapperRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userPostCommentMapperRepository = userPostCommentMapperRepository;
    }

    @Override
    @Transactional
    public String createComment(CommentDto commentDto) {

        User user = userRepository.findByUuid(commentDto.getUserUuid())
                .orElseThrow(() -> {
                    LOGGER.log(UserMessage.USER_NOT_FOUND + commentDto.getUserUuid(), HttpStatus.NOT_FOUND);
                    return null;
                });

        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.POST_NOT_FOUND + commentDto.getPostId(), HttpStatus.NOT_FOUND);
                    return null;
                });

        Comment comment = toEntity(commentDto);
        comment.setUserUuid(user.getUuid());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        UserPostCommentMapper userPostCommentMapper = userPostCommentMapperRepository.findByUserAndPost(user, post);
        if (userPostCommentMapper == null) {
            userPostCommentMapper = UserPostCommentMapper.builder()
                    .user(user)
                    .post(post)
                    .build();
            UserPostCommentMapper mapper = userPostCommentMapperRepository.save(userPostCommentMapper);
            savedComment.setUserPostCommentMapper(mapper);
        }
        savedComment.setUserPostCommentMapper(userPostCommentMapper);
        commentRepository.save(savedComment);
        return CommentMessage.COMMENT_CREATED_SUCCESS;
    }

    @Override
    @Transactional
    public String deleteComment(Long id, String userUuid) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.COMMENT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });

        if (!comment.getUserUuid().equals(userUuid)) {
            LOGGER.log(UserMessage.UNAUTHORIZED_ACTION + userUuid, HttpStatus.FORBIDDEN);
            return null;
        }

        commentRepository.deleteById(id);

        LOGGER.log(String.format(CommentMessage.COMMENT_DELETED_LOG, id));
        return CommentMessage.COMMENT_DELETED_SUCCESS;
    }

    @Override
    @Transactional
    public String updateComment(Long commentId, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.COMMENT_NOT_FOUND + commentId, HttpStatus.NOT_FOUND);
                    return null;
                });

        if (!existingComment.getUserUuid().equals(commentDto.getUserUuid())) {
            LOGGER.log(UserMessage.UNAUTHORIZED_ACTION + commentDto.getUserUuid(), HttpStatus.FORBIDDEN);
            return null;
        }

        existingComment.setDescription(commentDto.getDescription());
        existingComment.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        commentRepository.save(existingComment);
        return CommentMessage.COMMENT_UPDATED_SUCCESS;
    }

    @Override
    public Page<CommentDto> getAllComments(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Comment> comments = commentRepository.findAll(pageable);
        List<CommentDto> commentDtoList = comments.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(commentDtoList, pageable, comments.getTotalElements());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(CommentMessage.COMMENT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
        return toDto(comment);
    }

    private Comment toEntity(CommentDto commentDto) {
        return Comment.builder()
                .description(commentDto.getDescription())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    private CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .description(comment.getDescription())
                .createdDate(comment.getCreatedDate())
                .updateDate(comment.getUpdateDate())
                .userUuid(comment.getUserUuid())
                .postId(comment.getPost().getId())
                .build();
    }
}
