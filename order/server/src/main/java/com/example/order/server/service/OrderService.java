package com.example.order.server.service;

import com.example.order.server.dto.OrderDTO;

public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);
}
