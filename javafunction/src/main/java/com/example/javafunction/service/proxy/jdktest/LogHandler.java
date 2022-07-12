package com.example.javafunction.service.proxy.jdktest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author wangjiaxing
 * @Date 2022/4/30
 */
public class LogHandler implements InvocationHandler {

    private Object object;

    public LogHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("in logHandler "+ new Date());
        System.out.println("method:" + method.getName());
        Object invoke = method.invoke(object, args);
        System.out.println("out log handle");
        return invoke;
    }
}
