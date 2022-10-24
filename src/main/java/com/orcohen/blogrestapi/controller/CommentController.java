package com.orcohen.blogrestapi.controller;

import com.orcohen.blogrestapi.dto.CommentRequest;
import com.orcohen.blogrestapi.dto.CommentResponse;
import com.orcohen.blogrestapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.createComment(postId, commentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Set<CommentResponse>> getAllCommentsByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getAllCommentsForPost(postId), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> getCommentByCommentId(@PathVariable Long postId, @PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getCommentByCommentId(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}
