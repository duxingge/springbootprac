package com.example.serviceclient;

import com.example.serviceclient.controller.FileUploadController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wangjiaxing
 * @Date 2021/10/10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LimitTest {
    @Autowired
    public FileUploadController uploadController;


    @Test
    public void test() {
        uploadController.limitTest();
        uploadController.limitTest();
        uploadController.limitTest();
        uploadController.limitTest();
    }
}
