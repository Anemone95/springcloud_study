package com.example.product.service;

import com.example.product.dataobject.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpInfoAll();
}
