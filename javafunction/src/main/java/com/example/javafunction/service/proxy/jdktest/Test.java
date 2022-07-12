package com.example.javafunction.service.proxy.jdktest;

import java.lang.reflect.Proxy;

/**
 * @Author wangjiaxing
 * @Date 2022/4/30
 */
public class Test {
    public static void main(String[] args) {
        Student stu = new Student();
        IWork pro = (IWork) Proxy.newProxyInstance(stu.getClass().getClassLoader(), Student.class.getInterfaces(), new LogHandler(stu));
        pro.doWork("zhang san");

    }
}
