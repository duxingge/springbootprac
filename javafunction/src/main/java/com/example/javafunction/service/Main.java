package com.example.javafunction.service;


import java.util.Calendar;

/**
 * @Author wangjiaxing
 * @Date 2021/4/13
 */
public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Long endTime = calendar.getTime().getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        long fromTime = calendar.getTime().getTime();
        System.out.println(endTime-fromTime);
        System.out.println(60 * 60 * 24 * 1000);
    }
}
