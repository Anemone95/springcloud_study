package com.example.product.server.repository;

import com.example.product.server.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer status);
    List<ProductInfo> findByProductIdIn(List<String> ids);
}
