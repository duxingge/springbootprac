package com.example.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author wangjiaxing
 * @Date 2021/5/30
 */
@NoArgsConstructor
public class AccountRecord {
    private Integer id;
    private BigDecimal account;
    private String des;

    public AccountRecord(BigDecimal account, String des) {
        this.account = account;
        this.des = des;
    }
}
