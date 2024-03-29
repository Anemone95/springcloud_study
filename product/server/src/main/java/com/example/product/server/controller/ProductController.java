package com.example.product.server.controller;

import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import com.example.product.server.VO.ProductInfoVO;
import com.example.product.server.VO.ProductVO;
import com.example.product.server.dataobject.ProductCategory;
import com.example.product.server.dataobject.ProductInfo;
import com.example.product.server.service.CategoryService;
import com.example.product.server.service.ProductService;
import com.example.product.server.utils.ResultVOUtil;
import com.example.product.server.VO.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(){
        // 1.查询所有在架的商品
        List<ProductInfo> productInfoList=productService.findUpInfoAll();
        // 2.获取类目type列表
        List<Integer> categoryTypeList=productInfoList.stream()
                .map(ProductInfo::getCategoryType).collect(Collectors.toList());
        // 3.查询类目
        List<ProductCategory> categoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        // 4.构造数据
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表，给订单服务用
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public String decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) {
        productService.decreaseStock(cartDTOList);
        return "success";
    }
}
