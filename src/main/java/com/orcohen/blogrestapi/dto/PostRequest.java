package com.orcohen.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    @NotEmpty(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be less than 100 characters and more than 2 characters")
    private String title;
    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description must be more than 10 characters")
    private String description;
    @NotEmpty(message = "Content is required")
    private String content;
}
