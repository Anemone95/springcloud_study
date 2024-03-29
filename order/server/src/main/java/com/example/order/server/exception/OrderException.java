package com.example.order.server.exception;


import com.example.order.server.enums.ResultEnum;

public class OrderException extends RuntimeException {
    private Integer code;
    public OrderException(Integer code, String message){
        super(message);
        this.code=code;
    }
    public OrderException(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMessage());
    }
}
