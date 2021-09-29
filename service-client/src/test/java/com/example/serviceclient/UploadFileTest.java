package com.example.serviceclient;

import com.example.serviceclient.feign.FileUploadFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Author wangjiaxing
 * @Date 2021/5/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadFileTest {
    @Autowired
    private FileUploadFeign fileUploadFeign;
    @Test
    public void test() {
        try{
            File file1 = new File("/Users/wangjiaxing/idphoto.jpeg");
            File file2 = new File("/Users/wangjiaxing/idphoto.jpeg");

            MultipartFile multipartFile1 = new MockMultipartFile("test",new FileInputStream(file1));
            MultipartFile multipartFile2 = new MockMultipartFile("test",new FileInputStream(file2));
            MultipartFile[] multipartFiles = {multipartFile1,multipartFile2};
            fileUploadFeign.uploadOneFiles2(multipartFiles,"testUser","testName");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
