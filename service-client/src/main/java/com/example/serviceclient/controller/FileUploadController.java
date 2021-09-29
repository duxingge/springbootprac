package com.example.serviceclient.controller;

import com.example.serviceclient.feign.FileUploadFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Author wangjiaxing
 * @Date 2021/5/24
 */
@RestController
public class FileUploadController {
    @Autowired
    private FileUploadFeign fileUploadFeign;

    @GetMapping("test")
    public void testUploadFile() {
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
