package com.kit.dormitory.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapsedTimeMeasureAop {
//    @Around("execution(* com.kit.dormitory..*(..))")
    @Around("@annotation(com.kit.dormitory.annotation.ElapsedTimeLog)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        try
        {
            Object result = joinPoint.proceed();
            return result;
        }
        finally {
            stopwatch.stop();
            System.out.println(joinPoint.toString()+" "+"stopwatch.getTotalTimeMillis() = " + stopwatch.getTotalTimeMillis());

        }
    }
}
