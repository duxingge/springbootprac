package com.example.serviceclient.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @Author wangjiaxing
 * @Date 2022/3/7
 */
public class Book {
    @JsonAlias(value = "name")
    private String partnerName;

    @JsonAlias(value = "sok")
    private Boolean isOk;

    public Book() {
    }

    public Book(String name) {
        this.partnerName = name;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(Boolean ok) {
        isOk = ok;
    }
}
