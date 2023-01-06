package com.example.javafunction.service;

import java.io.Serializable;

/**
 * @Author wangjiaxing
 * @Date 2021/4/13
 */
public class Person implements Serializable {
    private LoanAuditStatuEnum statuEnum;
    private String name;

    public void say() {
        System.out.println("i am say");
    }

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

    public Person(LoanAuditStatuEnum statuEnum, String name) {
        this.statuEnum = statuEnum;
        this.name = name;
    }

    public Person() {
    }
}
