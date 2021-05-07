package com.example.javafunction.service;

/**
 * @Author wangjiaxing
 * @Date 2021/4/13
 */
public class Person {
    private LoanAuditStatuEnum statuEnum;
    private String name;

    public LoanAuditStatuEnum getStatuEnum() {
        return statuEnum;
    }

    public void setStatuEnum(LoanAuditStatuEnum statuEnum) {
        this.statuEnum = statuEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
