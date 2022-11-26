package com.orcohen.blogrestapi.security;

import com.orcohen.blogrestapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    public JwtTokenFilter(UserRepository userRepository, JwtTokenUtils jwtTokenUtils) {
        this.userRepository = userRepository;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal");
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // get the token
            String token = authHeader.substring(7);
            // validate the token
            boolean validToken;
            try {
                validToken = jwtTokenUtils.validateToken(token);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (validToken) {
                // get user identity and set it on the spring security context
                String username = jwtTokenUtils.getUserNameFromToken(token);
                var user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                var userDetails = new UserDetailsImpl(user);
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
