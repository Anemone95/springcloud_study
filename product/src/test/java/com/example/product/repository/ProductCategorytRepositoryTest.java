package com.example.product.repository;

import com.example.product.dataobject.ProductCategory;
import com.example.product.dataobject.ProductInfo;
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
public class ProductCategorytRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductCategory> list=productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11));
        Assert.assertTrue(list.size()>0);
    }
}