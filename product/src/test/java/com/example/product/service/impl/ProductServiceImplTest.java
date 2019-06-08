package com.example.product.service.impl;

import com.example.product.dto.CartDTO;
import com.example.product.dataobject.ProductInfo;
import com.example.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpInfoAll(){
        List<ProductInfo> productInfos=productService.findUpInfoAll();
        Assert.assertTrue(productInfos.size()>0);
    }

    @Test
    public void findList() {
        List<ProductInfo> productInfos=productService.findList(Arrays.asList("157875196366160022", "164103465734242707"));
        Assert.assertTrue(productInfos.size()>0);
    }

    @Test
    public void decreaseStock() {
        CartDTO cartDTO=new CartDTO();
        cartDTO.setProductId("157875196366160022");
        cartDTO.setProductQuantity(2);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}