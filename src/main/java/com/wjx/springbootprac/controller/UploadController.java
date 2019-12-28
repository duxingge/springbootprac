package com.wjx.springbootprac.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class UploadController {
    private final String dir = "E:\\study\\learndata";

    @PostMapping("/upload")
    public String upload(@RequestParam("uploadFile") MultipartFile multipartFile, HttpServletRequest request){
        try {
            InputStream input = multipartFile.getInputStream();
            String fileName = multipartFile.getOriginalFilename();
            String fileAllName = dir+File.separator+fileName;
            File f = new File(fileAllName);
            if(!f.exists()) {
                f.createNewFile();
            }
            FileUtils.copyInputStreamToFile(input,f);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ss";
    }

}
