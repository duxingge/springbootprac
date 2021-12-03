package com.example.common.util;


import com.example.common.annotions.Msg;

import java.lang.reflect.Method;

/**
 * @Author wangjiaxing
 * @Date 2021/11/19
 */
public class TestReflict {

    public static void main(String[] args) {

        try {
            Method getInstance = MyTest.class.getMethod("getInstance", new Class[0]);
            Msg annotation = getInstance.getAnnotation(Msg.class);
            System.out.println(annotation.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }



}
