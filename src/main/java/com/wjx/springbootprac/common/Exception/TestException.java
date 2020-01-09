package com.wjx.springbootprac.common.Exception;

import com.wjx.springbootprac.common.enums.ResultEnum;
import lombok.Data;

@Data
public class TestException extends RuntimeException {
    String code;
    public TestException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
