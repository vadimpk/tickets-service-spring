package com.naukma.ticketsservice.aspects;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    @Around("@annotation(com.naukma.ticketsservice.aspects.LogExecTime)")
    public Object logExecTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        // Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        // Measure method execution time
        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start(methodName);
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        // Log method execution time
        if (log.isInfoEnabled()) {
            log.info(stopWatch.prettyPrint());
        }
        return result;
    }

    @Before("@annotation(com.naukma.ticketsservice.aspects.LogInAndOutArgs)")
    public void logBefore(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        log.info("Executing method: " + methodName);

        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg: signatureArgs) {
            log.info("Incoming arg: " + signatureArg);
        }
    }

    @AfterReturning(value = "@annotation(com.naukma.ticketsservice.aspects.LogInAndOutArgs)", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Returned value: " + result);
    }
}
