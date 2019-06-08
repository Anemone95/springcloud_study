package com.example.product.service.impl;

import com.example.product.dto.CartDTO;
import com.example.product.dataobject.ProductInfo;
import com.example.product.enums.ProductStatusEnum;
import com.example.product.enums.ResultEnum;
import com.example.product.exception.ProductException;
import com.example.product.repository.ProductInfoRepository;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpInfoAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> ids) {
        return productInfoRepository.findByProductIdIn(ids);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfoOptional=productInfoRepository.findById(cartDTO.getProductId());
            // 商品不存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo=productInfoOptional.get(); //获取对象
            int result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if (result<0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }


    }
}
