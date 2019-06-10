package com.example.product.server.service;

import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import com.example.product.server.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpInfoAll();

    List<ProductInfoOutput> findList(List<String> ids);

    void decreaseStock(List<DecreaseStockInput> decreaseStockInputs);
}
