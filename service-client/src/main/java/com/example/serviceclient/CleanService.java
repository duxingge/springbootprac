package com.example.serviceclient;

import com.example.serviceclient.feign.SynTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangjiaxing
 * @Date 2022/3/3
 */
@Service
public class CleanService {

    @Autowired
    private SynTest synTest;

    ThreadPoolExecutor synPoolExecutor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    boolean end = false;
    int batch = 0;
    public void startClean() {
        long start = System.currentTimeMillis();
        while (!end) {
            List<String> batchs = getBatch();
            batchs.stream().forEach(
                    it -> {
                        synPoolExecutor.submit(new Runnable() {
                            @Override
                            public void run() {
                                synAgreement(it);
                            }
                        });
                    }

            );
            if (batchs.size() < 1000) {
                end = true;
            }
            System.out.println("[HistoryContractClean] "+(batch+1)*1000+" order's contract is syned");
        }
        long end = System.currentTimeMillis();
        System.out.println("Cost time :"+ (end-start)/1000);
    }

    public List<String> getBatch() {
        int start = batch * 1000;
        int end = (batch + 1) * 1000;
        if(batch>10) {
            end -=200;
        }
        List<String> results = new ArrayList<>();
        for (int i=start; i<end;i++) {
            results.add(String.valueOf(i));
        }
        batch++;
        return results;
    }
    public void synAgreement(String s) {
        System.out.println("Syn agreement " + s);
        synTest.clean(s);
    }

}
