package com.example.common.annotions;

import java.lang.annotation.*;

/**
 * @Author wangjiaxing
 * @Date 2021/11/19
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Msg {
    String value() default "default msg";
}
