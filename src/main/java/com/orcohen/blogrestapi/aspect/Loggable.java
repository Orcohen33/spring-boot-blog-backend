package com.orcohen.blogrestapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component        // Comment this line to disable the aspect
@Aspect
@Slf4j
public class Loggable {

//    @Before(value = "execution(* com.orcohen.blogrestapi.controller.*.*(..))")
//    public void log(JoinPoint joinPoint) {
//        log.info("================================Logging contollers================================");
//        log.info(joinPoint.getSignature().getName());
//    }

//    Loggin security
//    @Before(value = "execution(* com.orcohen.blogrestapi.auth.*.*(..))")
//    public void logSecurity(JoinPoint joinPoint) {
//        log.info("================================Logging security================================");
//        log.info(joinPoint.getSignature().getName());
//    }
//    @Before(value = "execution(* com.orcohen.blogrestapi.security.*.*(..))")
//    public void logSecurity2(JoinPoint joinPoint) {
//        log.info("================================Logging security2================================");
//        log.info(joinPoint.getSignature().getName());
//    }

    // log when a user is authenticated
//    @Before(value = "execution(* com.orcohen.blogrestapi.security.AuthenticationManagerImpl.*(..))")
//    public void logAuthentication(JoinPoint joinPoint) {
//        log.info("================================Logging authentication================================");
//        log.info(joinPoint.getSignature().getName());
//    }

}
