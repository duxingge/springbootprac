package com.example.serviceclient.pojo;

/**
 * @Author wangjiaxing
 * @Date 2022/5/23
 */
public class MitraValidateTokenParam {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MitraValidateTokenParam() {
    }

    public MitraValidateTokenParam(String token) {
        this.token = token;
    }
}
