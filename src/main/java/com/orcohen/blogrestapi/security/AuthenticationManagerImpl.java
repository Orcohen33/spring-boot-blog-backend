package com.orcohen.blogrestapi.security;

import com.orcohen.blogrestapi.entity.User;
import com.orcohen.blogrestapi.repository.UserRepository;
import com.orcohen.blogrestapi.config.PasswordConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
/**
 * This class is used to authenticate a user.
 * It is used by the AuthenticationController.
 */
public class AuthenticationManagerImpl implements AuthenticationManager {


    private final UserDetailsService userDetailsService;
    private final PasswordConfig passwordConfig;

    @Autowired
    public AuthenticationManagerImpl(UserDetailsService userDetailsService,
                                     PasswordConfig passwordConfig) {
        this.userDetailsService = userDetailsService;
        this.passwordConfig = passwordConfig;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("[AuthenticationManager] Authenticating user : {}", authentication.getName());
        String usernameOrEmail = authentication.getName().trim();
        String password = authentication.getCredentials().toString();

        var user = userDetailsService.loadUserByUsername(usernameOrEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        if(!passwordConfig.passwordEncoder().matches(password, user.getPassword())) {
            log.info("[AuthenticationManager] Authentication password failed for user : {}", usernameOrEmail);
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        return new UsernamePasswordAuthenticationToken(usernameOrEmail, password, user.getAuthorities());
    }

}