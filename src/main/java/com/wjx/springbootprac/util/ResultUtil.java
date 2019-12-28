package com.wjx.springbootprac.util;

import com.wjx.springbootprac.vo.Result;

public class ResultUtil {
    private static final String successCode = "0";
    private static final String successMessage = "请求成功";

    public static Result<Object> success(Object o){
        return new Result(successCode,successMessage,o);
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> error(String errorCode,String errorMessage) {
        return new Result(errorCode, errorMessage, null);
    }

    public static String getSuccessCode() {
        return successCode;
    }

    public static String getSuccessMessage() {
        return successMessage;
    }
}
