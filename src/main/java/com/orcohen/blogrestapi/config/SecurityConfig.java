package com.orcohen.blogrestapi.config;

import com.orcohen.blogrestapi.security.AuthenticationManagerImpl;
import com.orcohen.blogrestapi.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    //    private final AbstractUserDetailsAuthProviderImpl abstractUserDetailsAuthProvider;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public SecurityConfig(UserDetailsServiceImpl applicationUserService,
//                          AbstractUserDetailsAuthProviderImpl abstractUserDetailsAuthProvider,
                          AuthenticationManagerImpl authenticationManager) {
        this.userDetailsService = applicationUserService;
//        this.abstractUserDetailsAuthProvider = abstractUserDetailsAuthProvider;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // <--- disables csrf
                .authorizeRequests(auth -> auth
                        .antMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .authenticationManager(authenticationManager)   // <--- sets the authentication manager
//                .authenticationProvider(abstractUserDetailsAuthProvider) // <--- sets the authentication provider
                .userDetailsService(userDetailsService)// <--- sets the user details service
                .build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
////                .authenticationProvider(abstractUserDetailsAuthProvider) // <--- sets the authentication provider
//                .csrf().disable() // <--- disables csrf
//                .authorizeRequests(auth -> auth
//                        .antMatchers("/api/v1/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
////                .formLogin()
////                    .loginPage("/login")
////                    .permitAll()
////                    .loginProcessingUrl("/api/v1/auth/signin")
////                    .defaultSuccessUrl("/post", true)
////                .and()
//                .logout()
//                    .logoutUrl("/api/v1/auth/logouts")
//                    .logoutSuccessUrl("/api/v1/auth/logout-success")
//                .and()
//                .authenticationManager(authenticationManager)   // <--- sets the authentication manager
//                .authenticationProvider(daoAuthenticationProvider()) // <--- sets the authentication provider
//                .userDetailsService(userDetailsService)// <--- sets the user details service
//                .build();
//    }


}
