package com.example.order.server.message;

import com.example.order.server.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//    @StreamListener(StreamClient.INPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver: {}", message);
//    }

//    @StreamListener(StreamClient.INPUT)
//    public void process(CartDTO message) {
//        log.info("StreamReceiver: {}", message);
//    }

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.CALLBACK_OUTPUT)
    public String process(CartDTO message) {
        log.info("StreamReceiver: {}", message);
        return "received";
    }
    @StreamListener(StreamClient.CALLBACK_INPUT)
    public void callback(Object message) {
        log.info("CallbackReceiver: {}", message);
    }
}
