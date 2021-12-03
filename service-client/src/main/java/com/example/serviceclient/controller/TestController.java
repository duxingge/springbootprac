package com.example.serviceclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangjiaxing
 * @Date 2021/11/11
 */
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/m1")
    public String m1() {
        return "success";
    }
}
