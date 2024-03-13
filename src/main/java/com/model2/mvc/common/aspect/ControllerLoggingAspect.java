package com.model2.mvc.common.aspect;

import static com.model2.mvc.common.aspect.ConsoleColorCode.GREEN;
import static com.model2.mvc.common.aspect.ConsoleColorCode.RESET;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class ControllerLoggingAspect {
    Logger log = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    @Before("execution(* com.model2.mvc..*Controller.*(..))")
    public void logBeforeControllers(JoinPoint joinPoint) {
        log.debug("{}Class: {}", GREEN.getCode(), joinPoint.getTarget().getClass().getName());
        log.debug("Method: {}", joinPoint.getSignature().getName());
        log.debug("Signature: {}", joinPoint.getSignature());
        log.debug("Params: {}{}", Arrays.toString(joinPoint.getArgs()), RESET.getCode());
    }
}
