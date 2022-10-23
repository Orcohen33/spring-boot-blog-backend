package com.orcohen.blogrestapi.service.impl;

import com.orcohen.blogrestapi.dto.CommentRequest;
import com.orcohen.blogrestapi.dto.CommentResponse;
import com.orcohen.blogrestapi.entity.Comment;
import com.orcohen.blogrestapi.entity.Post;
import com.orcohen.blogrestapi.exception.ResourceNotFoundException;
import com.orcohen.blogrestapi.repository.CommentRepository;
import com.orcohen.blogrestapi.repository.PostRepository;
import com.orcohen.blogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentResponse createComment(Long postId, CommentRequest commentRequest) {
        Comment comment = mapCommentRequestToComment(commentRequest);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        return mapCommentToCommentResponse(newComment);
    }

    @Override
    public Set<CommentResponse> getAllCommentsForPost(Long postId) {
        // check if post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // get all comments for post
        Set<Comment> comments = post.getComments();
        // map comments to comment responses and return them
        return comments.stream()
                .map(this::mapCommentToCommentResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public CommentResponse getCommentByCommentId(Long postId, Long commentId) {
        // Check if post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // Get comment by comment id from post comments. If comment doesn't exist, throw exception
        Comment comment = post.getComments()
                .stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        // Map comment to comment response and return it
        return mapCommentToCommentResponse(comment);
    }

    @Override
    public CommentResponse updateComment(Long postId, Long commentId, CommentRequest commentRequest) {
        // Check if post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // Get comment by comment id from post comments. If comment doesn't exist, throw exception
        Comment comment = post.getComments()
                .stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        // Update comment
        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());
        comment.setEmail(commentRequest.getEmail());
        // Save updated comment
        Comment updatedComment = commentRepository.save(comment);
        // Map comment to comment response and return it
        return mapCommentToCommentResponse(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // Check if post exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // Get comment by comment id from post comments. If comment doesn't exist, throw exception
        Comment comment = post.getComments()
                .stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        // Delete comment
        commentRepository.delete(comment);
    }

    /* ----------------------------- PRIVATE METHODS ----------------------------- */

    private CommentResponse mapCommentToCommentResponse(Comment comment) {
        return modelMapper.map(comment, CommentResponse.class);
    }

    private Comment mapCommentRequestToComment(CommentRequest commentRequest) {
        return modelMapper.map(commentRequest, Comment.class);
    }
}
