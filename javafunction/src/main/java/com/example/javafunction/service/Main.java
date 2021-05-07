package com.example.javafunction.service;

import com.example.common.util.JsonUtil;

/**
 * @Author wangjiaxing
 * @Date 2021/4/13
 */
public class Main {
    public static void main(String[] args) {
        PersonStr p = new PersonStr();
        p.setName("testName");
        p.setStatuEnum("NOT_START");
        Person p2 = JsonUtil.parse(JsonUtil.toString(p), Person.class);
    }
}
