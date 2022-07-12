package com.example.hystrixcallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HystrixCallbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixCallbackApplication.class, args);
    }

}
