package com.example.serviceclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @Author wangjiaxing
 * @Date 2022/5/23
 */

@FeignClient(url = "http://localhost:8090/",name = "feignTest")
public interface FeignTest {

    @GetMapping("/test/people")
    String testFeign();
}
