package com.example.javafunction.service.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 高速并发线程安全缓存
 *
 * @Author wangjiaxing
 * @Date 2021/10/13
 */

public class CalculateCache<K, V> implements ICaculate<K, V> {

    ICaculate<K,V> c = null;

    public CalculateCache(ICaculate<K,V> c) {
        this.c = c;
    }

    private Map<K, FutureTask<V>> cacheResult = new ConcurrentHashMap<>();

    @Override
    public V caculate(K k) {
        try {
            if (cacheResult.get(k) == null) {
                FutureTask<V> newFutureTask = new FutureTask<>(new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.caculate(k);
                    }
                });

                FutureTask<V> preTask = cacheResult.putIfAbsent(k, newFutureTask);
                if (preTask == null) {
                    newFutureTask.run();
                }
            }
            return cacheResult.get(k).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            cacheResult.remove(k);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
