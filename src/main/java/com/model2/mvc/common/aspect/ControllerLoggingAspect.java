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
public class ControllerLoggingAspect {
    Logger log = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object logControllers(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("{}Class: {}", GREEN.getCode(), joinPoint.getTarget().getClass().getName());
        log.debug("Method: {}", joinPoint.getSignature().getName());
        log.debug("Signature: {}", joinPoint.getSignature());
        log.debug("Params: {}{}", Arrays.toString(joinPoint.getArgs()), RESET.getCode());
        Object returnObj = joinPoint.proceed();
        log.debug("{}[END]: {}", GREEN.getCode(), joinPoint.getSignature());
        if (returnObj != null) {
            log.debug("Return object: {}", returnObj.getClass());
        }
        log.debug("Return value: {}{}", returnObj, RESET.getCode());
        return returnObj;
    }
}
