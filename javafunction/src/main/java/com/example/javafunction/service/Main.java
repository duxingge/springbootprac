package com.example.javafunction.service;


import java.math.BigDecimal;

/**
 * @Author wangjiaxing
 * @Date 2021/4/13
 */
public class Main {

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(0.2);
        BigDecimal b2 = new BigDecimal(0.2);
        System.out.println("result"+ (b1 == b2));
    }
}
