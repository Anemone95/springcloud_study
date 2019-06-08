package com.example.product.service;

import com.example.product.dto.CartDTO;
import com.example.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpInfoAll();

    List<ProductInfo> findList(List<String> ids);

    void decreaseStock(List<CartDTO> cartDTOList);
}
