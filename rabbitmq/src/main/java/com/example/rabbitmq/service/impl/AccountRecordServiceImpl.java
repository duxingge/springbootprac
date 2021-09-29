package com.example.rabbitmq.service.impl;

import com.example.rabbitmq.mapper.AccountRecordMapper;
import com.example.rabbitmq.model.AccountRecord;
import com.example.rabbitmq.service.AccountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author wangjiaxing
 * @Date 2021/5/30
 */
@Service
public class AccountRecordServiceImpl implements AccountRecordService {
    @Autowired
    private AccountRecordMapper accountRecordMapper;
    @Override
    public void add(AccountRecord accountRecord) {
        int[] arr = new int[4];
        System.out.println(arr[6]);
        accountRecordMapper.add(accountRecord);
    }
}
