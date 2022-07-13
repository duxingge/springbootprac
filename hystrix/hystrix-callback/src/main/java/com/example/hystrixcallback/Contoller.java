package com.example.hystrixcallback;

import com.example.hystrixcallback.hystrix.MyService;
import com.example.hystrixcallback.hystrix.RequestCacheService;
import com.imooc.springcloud.Friend;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangjiaxing
 * @Date 2022/7/13
 */
@RestController
public class Contoller {
    @Autowired
    private MyService myService;

    @Autowired
    private RequestCacheService requestCacheService;

    @GetMapping("/fallback")
    public String fallback() {
        myService.sayHi();
        return myService.error();
    }


    @GetMapping("/timeout")
    public String timeout(Integer timeout) {
        return myService.retry(timeout);
    }

    @GetMapping("/timeout2")
    @HystrixCommand(
            fallbackMethod = "timeoutFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="3000")
            }
    )
    public String timeout2(Integer timeout) {
        return myService.retry(timeout);
    }

    public String timeoutFallback(Integer timeout) {
        return "success";
    }

    @GetMapping("/cache")
    public Friend cache(String name) {
        @Cleanup HystrixRequestContext context =
                HystrixRequestContext.initializeContext();

        Friend friend = requestCacheService.requestCache(name);
        friend = requestCacheService.requestCache(name);
        return friend;
    }

}
