package com.example.javafunction.service.multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 *
 * 多线程同时执行任务一个出错同时取消
 * @Author wangjiaxing
 * @Date 2022/9/27
 */
public class GroupWork {
    List<CompletableFuture> groupFutures = new ArrayList<>();


    public void add(Task<Boolean> task) {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> task.call()).thenAccept(result -> callback(result));
        groupFutures.add(completableFuture);
    }

    public void callback(Boolean result) {
        if(result==Boolean.FALSE) {
            groupFutures.stream().forEach(completableFuture -> completableFuture.cancel(true));
        }
    }

    boolean isEnd() {
        Optional<CompletableFuture> notEnd = groupFutures.stream().filter(it -> !(it.isDone() || it.isCancelled())).findAny();
        return !notEnd.isPresent();
    }

    public static void main(String[] args) throws InterruptedException {
        GroupWork groupWork = new GroupWork();
        groupWork.add(new Task<>("task1",5, Boolean.TRUE));
        groupWork.add(new Task<>("task2",2, Boolean.FALSE));
        groupWork.add(new Task<>("task3",10,Boolean.TRUE));
        while (!groupWork.isEnd()) {
            Thread.sleep(100);
        }
    }
}





class Task<Boolean> implements Callable {
    String name;
    long sleepSecond;
    Boolean result;

    public Task(String name, long sleepSecond, Boolean result) {
        this.name = name;
        this.sleepSecond = sleepSecond;
        this.result = result;
    }

    @Override
    public Boolean call()  {
        try {
            Thread.sleep(sleepSecond *1000);
            System.out.println(String.format("task of %s is done.",name));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

