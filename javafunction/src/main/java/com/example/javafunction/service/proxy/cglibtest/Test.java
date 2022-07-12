package com.example.javafunction.service.proxy.cglibtest;

import com.example.javafunction.service.proxy.jdktest.Student;

/**
 * @Author wangjiaxing
 * @Date 2022/4/30
 */
public class Test {
    public static void main(String[] args) {
        LogMethodProxy logMethodProxy = new LogMethodProxy();
        Student proxy = (Student) logMethodProxy.getProxy(Student.class);

        proxy.doWork("zhang san");


    }
}
