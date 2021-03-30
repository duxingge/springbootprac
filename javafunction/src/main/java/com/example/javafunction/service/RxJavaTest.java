package com.example.javafunction.service;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;

public class RxJavaTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
                observableEmitter.onNext("s1");

                observableEmitter.onNext("s2");
                observableEmitter.onNext("s3");
                System.out.println("current-thread: "+ Thread.currentThread());
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                writeFile((String) o);
                System.out.println("current-thread: "+ Thread.currentThread());
            }
        });
    }


    public static void writeFile(String s) throws IOException {
       FileOutputStream outputStream = new FileOutputStream("/Users/wangjiaxing/test.txt");
       IOUtils.write(s,outputStream);
    }
}
