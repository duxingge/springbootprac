package com.example.serviceclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author wangjiaxing
 * @Date 2022/3/3
 */
@FeignClient(name = "synTest" ,url = "http://localhost:8081")
public interface SynTest {

    @GetMapping("/consume")
    String clean(@RequestParam String s);
}
