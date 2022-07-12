package com.example.serviceclient.controller;

import com.example.common.util.JsonUtil;
import com.example.serviceclient.CleanService;
import com.example.serviceclient.feign.FeignTest;
import com.example.serviceclient.pojo.Book;
import com.example.serviceclient.pojo.MitraValidateTokenParam;
import com.example.serviceclient.pojo.MitraValidateTokenResult;
import com.example.serviceclient.pojo.People;
import com.example.serviceclient.service.TestService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @Author wangjiaxing
 * @Date 2021/11/11
 */
@RestController
@RequestMapping("")
public class TestController {

    int invokeTime=0;

    @Autowired
    private CleanService cleanService;

    @Autowired
    private TestService testService;

    @Autowired
    private FeignTest feignTest;

    @GetMapping("/m1")
    public String m1() {
        System.out.println("sss");
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
    private ResponseEntity testService() {
        People p = new People();
        p.setMain(new Book("math"));
        return ResponseEntity
                .status(402)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(p);
    }

    @GetMapping("/people")
    public People peopleBooks() {
        People p = new People();
        p.setMain(new Book("math"));
        p.setBooks(Lists.newArrayList(new Book("code"),new Book("art")));
        return p;
    }

    @GetMapping("/feign")
    public Map feignTest() {
        String resultStr = feignTest.testFeign();
        HashMap parse = JsonUtil.parse(resultStr, HashMap.class);
        return parse;

    }
    @PostMapping("/_partners/loan/applications/kpi/pinjaman-instan/token-verifications")
    public ResponseEntity<MitraValidateTokenResult> tokenVer(@RequestBody MitraValidateTokenParam param) {
        System.out.println("===============");
        MitraValidateTokenResult result = new MitraValidateTokenResult();
        result.setPhone_number("083242234441");
        return ResponseEntity
                .status(500)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(result);
    }


    public static void main(String[] args) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,2,28);

    }

}
