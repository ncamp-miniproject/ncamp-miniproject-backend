package com.model2.mvc.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("[Around before] applied method:: {}.{}",
                     joinPoint.getTarget().getClass().getName(),
                     joinPoint.getSignature().getName());
        logger.debug("[Around before] parameters:: {}", Arrays.toString(joinPoint.getArgs()));

        Object methodResult = joinPoint.proceed();

        System.out.println("[Around after] return: " + methodResult);
        return methodResult;
    }
}
