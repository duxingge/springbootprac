package com.wjx.springbootprac.Exception;

import com.wjx.springbootprac.enums.ResultEnum;
import lombok.Data;

@Data
public class TestException extends RuntimeException {
    String code;
    public TestException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
