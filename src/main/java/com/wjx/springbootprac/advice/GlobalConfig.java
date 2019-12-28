package com.wjx.springbootprac.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalConfig {

    @ModelAttribute("owner")
    public Map<String,String> globalVal1() {
        Map<String, String> map = new HashMap<>();
        map.put("author","wjx");
        map.put("company","duxingge");
        return map;
    }

    @InitBinder("a")
    public void init1(WebDataBinder dataBinder){
        dataBinder.setFieldDefaultPrefix("a.");
    }

    @InitBinder("b")
    public void init2(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("b.");
    }


}
