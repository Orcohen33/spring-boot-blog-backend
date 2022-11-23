//package com.orcohen.blogrestapi.security;
//
//import com.orcohen.blogrestapi.config.PasswordConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class AbstractUserDetailsAuthProviderImpl extends AbstractUserDetailsAuthenticationProvider {
//
//    private final UserDetailsService userDetailsService;
//    private final PasswordConfig passwordEncoder;
//
//    @Autowired
//    public AbstractUserDetailsAuthProviderImpl(UserDetailsServiceImpl userDetailsService, PasswordConfig passwordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        log.info("[AbstractUserDetailsAuthProvider] userDetails: {}", userDetails);
////        log.info("[AbstractUserDetailsAuthProvider] authentication: {}", authentication.getDetails().toString());
//        if (authentication.getCredentials() == null || userDetails.getPassword() == null) {
//            throw new AuthenticationException("Credentials may not be null.") {
//            };
//        }
//        if (!passwordEncoder.passwordEncoder().matches((String) authentication.getCredentials(), userDetails.getPassword())) {
//            throw new AuthenticationException("Invalid password.") {
//            };
//        }
//    }
//
//    @Override
//    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        log.info("[AbstractUserDetailsAuthProvider] username: {}", username);
//        return userDetailsService.loadUserByUsername(username);
//    }
//}
