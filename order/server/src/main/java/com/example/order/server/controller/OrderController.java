package com.example.order.server.controller;

import com.example.order.server.dto.OrderDTO;
import com.example.order.server.VO.ResultVO;
import com.example.order.server.converter.OrderForm2OrderDTO;
import com.example.order.server.enums.ResultEnum;
import com.example.order.server.exception.OrderException;
import com.example.order.server.form.OrderForm;
import com.example.order.server.service.OrderService;
import com.example.order.server.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 1.参数检验
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     */
    @PostMapping
    public ResultVO create(@Valid OrderForm orderForm,
                           BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.error("[Create Order] Invaild argument, orderFrom={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        // orderFrom->orderDTO
        OrderDTO orderDTO= OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[Create Order] Empty cart");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result=orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }
}
