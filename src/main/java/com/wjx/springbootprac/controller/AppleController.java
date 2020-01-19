package com.wjx.springbootprac.controller;

import com.wjx.springbootprac.po.Apple;
import com.wjx.springbootprac.service.AppleService;
import com.wjx.springbootprac.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apple")
public class AppleController {

    @Autowired
    private AppleService appleService;

    @GetMapping("/test")
    public Object test(){
        List<Apple> selectApples = appleService.getApple((Apple a) -> a.getColor().equals("green"));
//        Map<String, List<Apple>> collect = selectApples.stream().collect(groupingBy(Apple::getColor));
//        selectApples.stream().

        return ResultUtil.success(selectApples);
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello apple";
    }


}
