package com.example.javafunction.service.proxy.cglibtest;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author wangjiaxing
 * @Date 2022/4/30
 */
public class LogMethodProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class classfile) {
        enhancer.setSuperclass(classfile);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("in " + new Date());
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("out");
        return o1;
    }
}
