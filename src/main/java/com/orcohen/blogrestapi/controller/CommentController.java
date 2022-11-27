package com.orcohen.blogrestapi.controller;

import com.orcohen.blogrestapi.payload.CommentRequest;
import com.orcohen.blogrestapi.payload.CommentResponse;
import com.orcohen.blogrestapi.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Api(value = "CRUD Rest API for Comment resources", tags = "Comment")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "Create a new comment")
    @PostMapping("/posts/{postId}/comments")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.createComment(postId, commentRequest), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all comments by post id")
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Set<CommentResponse>> getAllCommentsByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getAllCommentsForPost(postId), HttpStatus.OK);
    }
    @ApiOperation(value = "Get a comment by id and post id")
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> getCommentByCommentId(@PathVariable Long postId, @PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getCommentByCommentId(postId, commentId), HttpStatus.OK);
    }
    @ApiOperation(value = "Update a comment by id and post id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentRequest), HttpStatus.OK);
    }
    @ApiOperation(value = "Delete a comment by id and post id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}
