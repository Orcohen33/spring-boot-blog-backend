package com.orcohen.blogrestapi.repository;

import com.orcohen.blogrestapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
