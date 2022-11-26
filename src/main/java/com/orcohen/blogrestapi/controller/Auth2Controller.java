package com.orcohen.blogrestapi.controller;

import com.orcohen.blogrestapi.payload.SignInRequest;
import com.orcohen.blogrestapi.security.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class Auth2Controller {

    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public Auth2Controller(JwtTokenUtils jwtTokenUtils,
                           AuthenticationManager authenticationManager) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public String getToken(@RequestBody SignInRequest userLogin){
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsernameOrEmail(), userLogin.getPassword()));
        // Set the authentication in the context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generate the token back to client
        return jwtTokenUtils.generateToken(authentication);
    }

    @GetMapping("/secure")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String secure() {
        return "<h1>Secure</h1>";
    }



}
