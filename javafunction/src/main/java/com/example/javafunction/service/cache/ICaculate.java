package com.example.javafunction.service.cache;

/**
 * @Author wangjiaxing
 * @Date 2021/10/13
 */
public interface ICaculate<K,V> {
    V caculate(K k) throws InterruptedException;
}
