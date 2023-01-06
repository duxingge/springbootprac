package com.example.javafunction.service.proxy.jdktest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author wangjiaxing
 * @Date 2022/4/30
 */
public class Test {
    public static void main(String[] args) {
//        Student stu = new Student();
//        IWork pro = (IWork) Proxy.newProxyInstance(stu.getClass().getClassLoader(), Student.class.getInterfaces(), new LogHandler(stu));
//        pro.doWork("zhang san");
        Unsafe unsafe = getUnsafe();
        
        System.out.println(unsafe);
    }


    static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
