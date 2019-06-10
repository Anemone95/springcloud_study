package com.example.order.server.service.impl;

import com.example.order.server.dto.OrderDTO;
import com.example.order.server.dataobject.OrderDetail;
import com.example.order.server.dataobject.OrderMaster;
import com.example.order.server.dto.CartDTO;
import com.example.order.server.enums.OrderStatusEnum;
import com.example.order.server.enums.PayStatusEnum;
import com.example.order.server.repository.OrderDetailRepository;
import com.example.order.server.repository.OrderMasterRepository;
import com.example.order.server.service.OrderService;
import com.example.order.server.utils.KeyUtil;
import com.example.product.client.ProductClient;
import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId=KeyUtil.genUniqueKey();
        // 查询商品信息（调用商品服务）
        List<String> productIdList=orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 计算总价
        BigDecimal sum=new BigDecimal(0);
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            for ( ProductInfoOutput productInfo:productInfoList ) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    sum=sum.add(
                            productInfo.getProductPrice().multiply(
                                    new BigDecimal(orderDetail.getProductQuantity())));
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        
        // 扣库存（调用商品服务）
        List<DecreaseStockInput> decreaseStockInputs=orderDTO.getOrderDetailList().stream()
                .map(e->new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputs);
        // 入库
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
