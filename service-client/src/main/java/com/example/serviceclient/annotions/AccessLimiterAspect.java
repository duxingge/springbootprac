package com.example.serviceclient.annotions;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author wangjiaxing
 * @Date 2021/10/10
 */
@Aspect
@Slf4j
@Component
public class AccessLimiterAspect {

    @Autowired
    private RedisScript rateLimitScript;

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut(value = "@annotation(com.example.serviceclient.annotions.AccessLimiter)")
    public void pointCut(){
        log.info("enter cut");
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AccessLimiter annotation = method.getAnnotation(AccessLimiter.class);
        int limit = annotation.limit();
        String key = annotation.methodKey();
        StringBuilder redisKey = new StringBuilder();
        if(StringUtils.isNotBlank(key)) {
            redisKey.append(key);
        }else {
            redisKey.append(method.getName());
        }
        log.info("redisKey: {}",redisKey);
        if(!passRedisLimit(redisKey,limit)) {
            log.error("reach limit!");
        }
    }

    private boolean passRedisLimit(StringBuilder redisKey,int limit) {
        Boolean pass = (Boolean) redisTemplate.execute(rateLimitScript, Lists.newArrayList(redisKey.toString()), String.valueOf(limit));
        return pass;
    }
}
