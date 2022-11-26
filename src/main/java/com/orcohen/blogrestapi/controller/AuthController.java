package com.orcohen.blogrestapi.controller;

import com.orcohen.blogrestapi.entity.Role;
import com.orcohen.blogrestapi.entity.User;
import com.orcohen.blogrestapi.exception.ResourceNotFoundException;
import com.orcohen.blogrestapi.payload.SignInRequest;
import com.orcohen.blogrestapi.payload.SignUpRequest;
import com.orcohen.blogrestapi.repository.RoleRepository;
import com.orcohen.blogrestapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
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
        log.info("[AuthController] Authentication: {}", authentication);
        log.info("[AuthController] SecurityContextHolder: {}", SecurityContextHolder.getContext().getAuthentication());
        log.info("[AuthController] getName(): {}", SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("[AuthController] getAuthorities(): {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        log.info("[AuthController] getPrincipal(): {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        log.info("[AuthController] getCredentials(): {}", SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        return ResponseEntity.ok("Logged in successfully");
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        log.info("================================Logging register================================");
        if(userService.isUserExistByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if(userService.isUserExistByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Create new user's account
        User user = User.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", "USER"));
        user.setRoles(Collections.singleton(userRole));
        userService.saveUser(user);

        log.info("User registered successfully!");

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        log.info("================================Logging logout================================");
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        SecurityContextHolder.clearContext();
        log.info("Logged out successfully");
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            log.info(SecurityContextHolder.getContext().getAuthentication().getName());
            log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
            log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        }
        return new ResponseEntity<>("Logout successfully", HttpStatus.OK);
    }

    @GetMapping("/security-details")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> securityDetails() {
        log.info("================================Logging securityDetails================================");
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        log.info(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        return new ResponseEntity<>("Security details", HttpStatus.OK);
    }




}
