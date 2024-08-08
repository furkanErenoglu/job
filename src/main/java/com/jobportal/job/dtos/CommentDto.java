package com.jobportal.job.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String description;
    private String userUuid;
    private Timestamp createdDate;
    private Timestamp updateDate;
    private Long postId;
}
