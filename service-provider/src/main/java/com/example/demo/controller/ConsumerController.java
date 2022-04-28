package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author wangjiaxing
 * @Date 2022/3/3
 */
@RestController
public class ConsumerController {
     AtomicInteger c = new AtomicInteger();
    @GetMapping("/consume")
    public String consume(@RequestParam String s) {
        try {
            c.incrementAndGet();
            System.out.println("receive :" + s + " c: "+ c.get());
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "consume success";
    }
}
