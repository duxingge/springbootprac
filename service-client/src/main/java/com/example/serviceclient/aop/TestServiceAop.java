package com.example.serviceclient.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author wangjiaxing
 * @Date 2022/3/9
 */
@Aspect
@Component
public class TestServiceAop {

    @Around("execution(* com.example.serviceclient.service..*(..))")
    public Object doLog(ProceedingJoinPoint point) throws Throwable {
        System.out.println("method: "+ point.getSignature().getName());
        return point.proceed();
    }
}
