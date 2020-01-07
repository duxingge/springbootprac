package com.wjx.springbootprac.advice;

import com.wjx.springbootprac.Exception.TestException;
import com.wjx.springbootprac.util.ResultUtil;
import com.wjx.springbootprac.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e){
        System.out.println(e.getMessage());
        if(e instanceof TestException) {
            TestException testException = (TestException) e;
            return ResultUtil.error(testException.getCode(),testException.getMessage());
        }else {
            System.out.println("系统异常");
            return ResultUtil.error("-1","未知错误");
        }
    }
}
