package com.orcohen.blogrestapi.service;

import com.orcohen.blogrestapi.dto.CommentRequest;
import com.orcohen.blogrestapi.dto.CommentResponse;

import java.util.Set;

public interface CommentService {
    CommentResponse createComment(Long postId, CommentRequest commentRequest);

    Set<CommentResponse> getAllCommentsForPost(Long postId);

    CommentResponse getCommentByCommentId(Long postId, Long commentId);

    CommentResponse updateComment(Long postId, Long commentId, CommentRequest commentRequest);

    void deleteComment(Long postId, Long commentId);
}
