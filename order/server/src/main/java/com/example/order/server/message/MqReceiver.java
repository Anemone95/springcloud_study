package com.example.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受MQ消息
 */
@Slf4j
@Component
public class MqReceiver {

    // 接收队列名
    // 手动创建队列
//    @RabbitListener(queues = "myQueue")
//    public void process(String message){
//        log.info("MyReceiver: {}", message);
//    }
    // 自动创建队列
    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String message){
        log.info("MyReceiver: {}", message);
    }

    // 自动创建队列并绑定exchange
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message){
        log.info("Computer Receiver: {}", message);
    }
}
