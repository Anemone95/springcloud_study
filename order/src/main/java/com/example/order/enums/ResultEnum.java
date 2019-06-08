package com.example.order.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "Argument Error"),
    CART_EMPTY(2, "CART Error"),
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }
}
