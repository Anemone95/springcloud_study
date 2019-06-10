package com.example.product.server.exception;

import com.example.product.server.enums.ResultEnum;

public class ProductException extends RuntimeException {
    private Integer code;
    public ProductException(Integer code, String message) {
        super(message);
        this.code=code;
    }
    public ProductException(ResultEnum resultEnum) {
        this(resultEnum.getCode(),resultEnum.getMessage());
    }
}
