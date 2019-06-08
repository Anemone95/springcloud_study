package com.example.product.service.impl;

import com.example.product.ProductApplicationTests;
import com.example.product.dataobject.ProductInfo;
import com.example.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpInfoAll(){
        List<ProductInfo> productInfos=productService.findUpInfoAll();
        Assert.assertTrue(productInfos.size()>0);
    }
}
