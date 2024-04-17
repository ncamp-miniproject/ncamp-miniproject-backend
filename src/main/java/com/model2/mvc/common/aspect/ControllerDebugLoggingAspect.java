package com.model2.mvc.common.aspect;

import static com.model2.mvc.common.aspect.ConsoleColorCode.GREEN;
import static com.model2.mvc.common.aspect.ConsoleColorCode.RESET;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class ControllerDebugLoggingAspect {
    Logger log = LoggerFactory.getLogger(ControllerDebugLoggingAspect.class);

    public ControllerDebugLoggingAspect() {
        System.out.println("================");
        System.out.println("Initialize ControllerLoggingAspect");
        System.out.println("======================");
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object logControllers(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("{}Class: {}{}", GREEN.getCode(), joinPoint.getTarget().getClass().getName(), RESET.getCode());
        log.debug("{}Method: {}{}", GREEN.getCode(), joinPoint.getSignature().getName(), RESET.getCode());
        log.debug("{}Signature: {}{}", GREEN.getCode(), joinPoint.getSignature(), RESET.getCode());
        log.debug("{}Params: {}{}", GREEN.getCode(), Arrays.toString(joinPoint.getArgs()), RESET.getCode());
        Object returnObj = joinPoint.proceed();
        log.debug("{}[END]: {}", GREEN.getCode(), joinPoint.getSignature());
        if (returnObj != null) {
            log.debug("{}Return object: {}{}", GREEN.getCode(), returnObj.getClass(), RESET.getCode());
        }
        log.debug("{}Return value: {}{}", GREEN.getCode(), returnObj, RESET.getCode());
        return returnObj;
    }
}
