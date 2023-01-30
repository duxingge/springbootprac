package com.example.javafunction.service.wandou.execel;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author wangjiaxing
 * @Date 2023/1/28
 */
public class Rename {
    public static void main(String[] args) throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvserror");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            String c= files[i].getParent();
            File mm=new File(c+"new"+i+".txt");
            file1.renameTo(mm);
        }

    }
}
