package com.example.serviceclient.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author wangjiaxing
 * @Date 2021/5/24
 */
@FeignClient(url = "http://localhost:8080/",name = "fileUploadFeign")
public interface FileUploadFeign {
    @PostMapping(value = "upload/files2",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void uploadOneFiles2(@RequestPart("files") MultipartFile[] files, @RequestParam("user") String user, @RequestParam("name") String name);
}
