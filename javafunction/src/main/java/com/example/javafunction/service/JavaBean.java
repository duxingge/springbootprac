package com.example.javafunction.service;

/**
 * @Author wangjiaxing
 * @Date 2022/10/19
 */
class JavaBean{
    private int value = 1;
    public String s = "abc";
    public final static int f = 0x101;

    public void setValue(int v){
        final int temp = 3;
        this.value = temp + v;
    }

    public int getValue(){
        return value;
    }
}
