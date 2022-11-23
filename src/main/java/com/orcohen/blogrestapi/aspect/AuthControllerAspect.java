//package com.orcohen.blogrestapi.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//@Slf4j
//public class AuthControllerAspect {
//
//    @Before(value = "execution(* com.orcohen.blogrestapi.controller.AuthController.*(..))")
//    public void log(JoinPoint joinPoint) {
//        log.info("================================Logging AuthController================================");
//        log.info(joinPoint.getSignature().getName());
//    }
//}
