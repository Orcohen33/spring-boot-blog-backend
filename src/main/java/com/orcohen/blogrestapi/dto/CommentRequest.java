package com.orcohen.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {
    @NotEmpty(message = "Name is required")
    @Size(min = 2, message = "Name must be more than 2 characters.")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Comment is required")
    @Size(min = 2, message = "Comment must be more than 2 characters.")
    private String body;
}
