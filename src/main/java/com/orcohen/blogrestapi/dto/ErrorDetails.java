package com.orcohen.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorDetails {
    private Date timestamps;
    private String message;
    private String details;
}
