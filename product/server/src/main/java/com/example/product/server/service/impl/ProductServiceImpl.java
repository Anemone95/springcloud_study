package com.example.product.server.service.impl;

import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import com.example.product.server.dataobject.ProductInfo;
import com.example.product.server.enums.ResultEnum;
import com.example.product.server.exception.ProductException;
import com.example.product.server.repository.ProductInfoRepository;
import com.example.product.server.service.ProductService;
import com.example.product.server.enums.ProductStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpInfoAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> ids) {
        List<ProductInfo> productInfos= productInfoRepository.findByProductIdIn(ids);
        return productInfos.stream()
                .map(e->{
                    ProductInfoOutput output=new ProductInfoOutput();
                    BeanUtils.copyProperties(e,output);
                    return output;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputs) {
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputs) {
            Optional<ProductInfo> productInfoOptional=productInfoRepository.findById(decreaseStockInput.getProductId());
            // 商品不存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo=productInfoOptional.get(); //获取对象
            int result=productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
            if (result<0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }


    }
}
