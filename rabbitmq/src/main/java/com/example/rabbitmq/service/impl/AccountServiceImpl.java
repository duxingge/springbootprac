package com.example.rabbitmq.service.impl;

import com.example.rabbitmq.mapper.AccountMapper;
import com.example.rabbitmq.mapper.AccountRecordMapper;
import com.example.rabbitmq.model.AccountRecord;
import com.example.rabbitmq.service.AccountRecordService;
import com.example.rabbitmq.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Author wangjiaxing
 * @Date 2021/5/30
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRecordService recordService;

    @Override
    @Transactional
    public void minus(Integer id, BigDecimal amount) {
        accountMapper.add(id,amount.negate());
        recordService.add(new AccountRecord(amount,"扣除"));
    }

    @Override
    @Transactional
    public void add(Integer id, BigDecimal amount) {
        accountMapper.add(id,amount);
        recordService.add(new AccountRecord(amount,"增加"));
    }
}
