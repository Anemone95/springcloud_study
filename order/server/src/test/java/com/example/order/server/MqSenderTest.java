package com.example.order.server;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MqSenderTest extends ServerApplicationTests{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        // 队列，消息体
        amqpTemplate.convertAndSend("myQueue", "now "+new Date());
    }

    @Test
    public void sendOrder(){
        // exchange, routingKey, message
        amqpTemplate.convertAndSend("myOrder", "computer", "computer order.");
    }
}
