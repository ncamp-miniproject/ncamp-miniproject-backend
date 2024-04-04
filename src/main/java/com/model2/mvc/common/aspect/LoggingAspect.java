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
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect() {
        System.out.println("============================");
        System.out.println("LoggingAspect");
        System.out.println("==========================");
    }

    @Around("execution(* com.model2.mvc..*ServiceImpl.*(..))")
    public Object aroundServiceImpl(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("{}[Around before] applied method:: {}.{}{}",
                  CYAN.getCode(),
                  joinPoint.getTarget().getClass().getName(),
                  joinPoint.getSignature().getName(),
                  RESET.getCode());
        log.info("{}[Around before] parameters:: {}{}", CYAN.getCode(), Arrays.toString(joinPoint.getArgs()), RESET.getCode());

        Object methodResult = joinPoint.proceed();

        log.info("{}[Around after] return: {}{}", CYAN.getCode(), methodResult, RESET.getCode());
        return methodResult;
    }

    @Around("execution(* com.model2.mvc..*DAO.*(..)) || execution(public * com.model2.mvc..*Repository.*(..))")
    public Object logAroundDAO(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("{}Class: {}", YELLOW.getCode(), joinPoint.getTarget().getClass().getName());
        log.info("Method: {}", joinPoint.getSignature().getName());
        log.info("Signature: {}", joinPoint.getSignature());
        log.info("Params: {}{}", Arrays.toString(joinPoint.getArgs()), RESET.getCode());

        Object returnValue = joinPoint.proceed();

        log.info("{}Returns: {}{}", YELLOW.getCode(), returnValue, RESET.getCode());
        return returnValue;
    }
}
