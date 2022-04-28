package com.example.serviceclient.controller;

import com.example.serviceclient.CleanService;
import com.example.serviceclient.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.*;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

/**
 * @Author wangjiaxing
 * @Date 2021/11/11
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private CleanService cleanService;

    @Autowired
    private TestService testService;

    @GetMapping("/m1")
    public String m1() {
        return "success";
    }
    @GetMapping("/clean")
    public String clean() {
        CompletableFuture.runAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        cleanService.startClean();
                    }
                }
        );
        return "success";
    }

    @GetMapping("/m2")
    private String testService() {
        return testService.test();
    }


    public static void main(String[] args) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,2,28);

    }
}
