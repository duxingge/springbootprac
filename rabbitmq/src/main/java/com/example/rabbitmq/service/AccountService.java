package com.example.rabbitmq.service;

import java.math.BigDecimal;

/**
 * @Author wangjiaxing
 * @Date 2021/5/30
 */
public interface AccountService {
    void minus(Integer id, BigDecimal amount);
    void add(Integer id, BigDecimal amount);
}
