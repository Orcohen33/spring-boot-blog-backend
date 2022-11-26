package com.orcohen.blogrestapi.payload;

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
