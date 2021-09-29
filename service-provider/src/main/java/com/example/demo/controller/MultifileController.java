package com.example.demo.controller;

import com.example.common.util.JsonUtil;
import com.example.demo.bo.FileInfoBO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author wangjiaxing
 * @Date 2021/5/24
 */
@RestController
@RequestMapping("upload")
public class MultifileController {

    @PostMapping("/files")
    public void uploadOneFiles(@RequestParam("files") MultipartFile[] files, @SpringQueryMap FileInfoBO fileInfoBO) {
        System.out.println("接收到文件数：" + files.length);
        System.out.println(JsonUtil.toString(fileInfoBO));
    }

    @PostMapping("/files2")
    public void uploadOneFiles2(@RequestParam("files") MultipartFile[] files, @RequestParam("user") String user,@RequestParam("name") String name) {
        System.out.println("接收到文件数：" + files.length);
        System.out.println(user+" "+name);
    }
    @PostMapping("/file")
    public void uploadOneFile(@RequestParam("file") MultipartFile file, @SpringQueryMap FileInfoBO fileInfoBO) {
        System.out.println("接收到文件数：" + file!=null?1:0);
        System.out.println(JsonUtil.toString(fileInfoBO));
    }
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}
