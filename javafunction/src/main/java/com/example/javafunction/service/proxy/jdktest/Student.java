package com.example.javafunction.service.proxy.jdktest;

/**
 * @Author wangjiaxing
 * @Date 2022/4/30
 */
public class Student implements IWork {
    @Override
    public void doWork(String name) {
        System.out.println(name+ " study.....");
    }
}
