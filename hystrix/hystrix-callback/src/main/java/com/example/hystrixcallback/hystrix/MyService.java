package com.example.hystrixcallback.hystrix;

import com.imooc.springcloud.IService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author wangjiaxing
 * @Date 2022/7/13
 */
@FeignClient(name = "feign-client",fallback = Fallback.class)
public interface MyService extends IService {

}
