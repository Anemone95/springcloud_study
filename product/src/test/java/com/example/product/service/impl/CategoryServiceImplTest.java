package com.example.product.service.impl;

import com.example.product.ProductApplicationTests;
import com.example.product.dataobject.ProductCategory;
import com.example.product.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class CategoryServiceImplTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories=categoryService.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(productCategories.size()>0);
    }
}