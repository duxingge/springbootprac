package com.example.common.util;

import com.example.common.annotions.Msg;

/**
 * @Author wangjiaxing
 * @Date 2021/10/26
 */
public class MyTest {
    private volatile static MyTest test;

    @Msg
    public static MyTest getInstance() {
        if(MyTest.test ==null) {
            synchronized (MyTest.class) {
                if(MyTest.test ==null) {
                    MyTest.test = new MyTest();
                }
            }

        }
        return MyTest.test;
    }
}
