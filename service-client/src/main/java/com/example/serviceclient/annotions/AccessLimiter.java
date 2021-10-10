package com.example.serviceclient.annotions;

import java.lang.annotation.*;

/**
 * @Author wangjiaxing
 * @Date 2021/10/10
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AccessLimiter {
    int limit();
    String methodKey() default "";
}
