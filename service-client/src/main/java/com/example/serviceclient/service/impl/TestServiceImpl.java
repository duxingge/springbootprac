package com.example.serviceclient.service.impl;

import com.example.serviceclient.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Author wangjiaxing
 * @Date 2022/3/9
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "ssss";
    }
}
