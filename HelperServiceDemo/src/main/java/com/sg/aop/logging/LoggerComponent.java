package com.sg.aop.logging;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggerComponent {

    private static final String POINTCUT = "within(com.sg.controller.*)";
    
    @Around(POINTCUT)
    @SneakyThrows
    public Object logArroundExec(ProceedingJoinPoint pjp) {
        log.info("[AspectLog]: before Method {}", constructLogMsg(pjp));
        var proceed = pjp.proceed();
        log.info("[AspectLog]: after Method {} with result: {}",constructLogMsg(pjp), proceed.toString());
        return proceed;
    }

    @AfterThrowing(pointcut = POINTCUT, throwing = "e")
    public void logAfterException(JoinPoint jp, Exception e) {
        log.error("[AspectLog]: Exception during: {} with ex: {}", constructLogMsg(jp),  e.toString());
    }

    private String constructLogMsg(JoinPoint jp) {
        var args = Arrays.asList(jp.getArgs()).stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));
        String className = jp.getSignature().getDeclaringTypeName();
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        var sb = new StringBuilder(className);
        sb.append(".");
        sb.append(method.getName());
        sb.append("(");
        sb.append(args);
        sb.append(")");
        return sb.toString();
    }
}