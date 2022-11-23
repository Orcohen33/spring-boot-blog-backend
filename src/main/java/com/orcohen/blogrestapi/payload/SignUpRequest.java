package com.orcohen.blogrestapi.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class SignUpRequest {
    @Min(3) @Max(20)
    private String name;
    @Min(4) @Max(20)
    private String username;
    @Email
    private String email;
    @Min(6) @Max(20)
    private String password;
}
