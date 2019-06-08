package com.example.product.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1, "Product not exist"),
    PRODUCT_STOCK_ERROR(2, "Stock Error"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }
}
