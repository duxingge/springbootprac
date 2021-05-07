package com.example.javafunction.service;

/**
 * @Author wangjiaxing
 * @Date 2021/4/13
 */
public enum LoanAuditStatuEnum {

    NOT_START(0),

    APPROVED(10),

    UNDER_APPROVAL(20),

    RETURNED(30),

    REJECTED(40),

    RECOMMENDED(50);

    public int value;

    LoanAuditStatuEnum(int value) {
        this.value = value;
    }

}
