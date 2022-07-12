package com.example.hystrixcallback.hystrix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author wangjiaxing
 * @Date 2022/7/13
 */
@Slf4j
@Service
public class MyService {

    public void error() {
        log.info("Fallback: I'm not a black sheep any more");
        throw new RuntimeException("first fallback");
    }
}
