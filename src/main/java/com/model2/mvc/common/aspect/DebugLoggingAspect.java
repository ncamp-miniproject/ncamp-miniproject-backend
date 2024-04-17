package com.model2.mvc.common.aspect;

import static com.model2.mvc.common.aspect.ConsoleColorCode.CYAN;
import static com.model2.mvc.common.aspect.ConsoleColorCode.RESET;
import static com.model2.mvc.common.aspect.ConsoleColorCode.YELLOW;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class DebugLoggingAspect {
    Logger log = LoggerFactory.getLogger(DebugLoggingAspect.class);

    public DebugLoggingAspect() {
        System.out.println("============================");
        System.out.println("LoggingAspect");
        System.out.println("==========================");
    }

    @Around("execution(* com.model2.mvc..*ServiceImpl.*(..))")
    public Object aroundServiceImpl(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("{}[Around before] applied method:: {}.{}{}",
                  CYAN.getCode(),
                  joinPoint.getTarget().getClass().getName(),
                  joinPoint.getSignature().getName(),
                  RESET.getCode());
        log.debug("{}[Around before] parameters:: {}{}", CYAN.getCode(), Arrays.toString(joinPoint.getArgs()), RESET.getCode());

        Object methodResult = joinPoint.proceed();

        log.debug("{}[Around after] return: {}{}", CYAN.getCode(), methodResult, RESET.getCode());
        return methodResult;
    }

    @Around("execution(* com.model2.mvc..*DAO.*(..)) || execution(public * com.model2.mvc..*Repository.*(..))")
    public Object logAroundDAO(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("{}Class: {}{}", YELLOW.getCode(), joinPoint.getTarget().getClass().getName(), RESET.getCode());
        log.debug("{}Method: {}{}", YELLOW.getCode(), joinPoint.getSignature().getName(), RESET.getCode());
        log.debug("{}Signature: {}{}", YELLOW.getCode(), joinPoint.getSignature(), RESET.getCode());
        log.debug("{}Params: {}{}", YELLOW.getCode(), Arrays.toString(joinPoint.getArgs()), RESET.getCode());

        Object returnValue = joinPoint.proceed();

        log.debug("{}Returns: {}{}", YELLOW.getCode(), returnValue, RESET.getCode());
        return returnValue;
    }
}
