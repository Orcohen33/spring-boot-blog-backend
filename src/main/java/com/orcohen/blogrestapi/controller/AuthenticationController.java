package com.orcohen.blogrestapi.controller;

import com.orcohen.blogrestapi.entity.Role;
import com.orcohen.blogrestapi.entity.User;
import com.orcohen.blogrestapi.exception.ResourceNotFoundException;
import com.orcohen.blogrestapi.payload.SignInRequest;
import com.orcohen.blogrestapi.payload.SignUpRequest;
import com.orcohen.blogrestapi.security.JwtTokenUtils;
import com.orcohen.blogrestapi.service.RoleService;
import com.orcohen.blogrestapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthenticationController {

    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(JwtTokenUtils jwtTokenUtils,
                                    AuthenticationManager authenticationManager,
                                    UserService userService,
                                    RoleService roleService,
                                    PasswordEncoder passwordEncoder) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsernameOrEmail(),
                        signInRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("[AuthController] User {} logged in successfully", signInRequest.getUsernameOrEmail());
        String jwt = jwtTokenUtils.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userService.isUserExistByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userService.isUserExistByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email Address already in use!", HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .build();

        Role userRole = roleService.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", "USER"));
        user.setRoles(Collections.singleton(userRole));
        userService.saveUser(user);
        log.info("[AuthController] User {} registered successfully", signUpRequest.getUsername());
        return ResponseEntity.ok("User: "+signUpRequest.getUsername()+" registered successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        log.info("[AuthController] User {} logged out successfully", SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("[AuthController] SecurityContextHolder {}", SecurityContextHolder.getContext().getAuthentication());
        SecurityContextHolder.clearContext();
        log.info("[AuthController] User logged out successfully");
        return ResponseEntity.ok("User logged out successfully");
    }
}
