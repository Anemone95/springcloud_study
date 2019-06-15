package com.example.order.server.controller;

import com.example.order.server.dto.CartDTO;
import com.example.order.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        streamClient.output().send(MessageBuilder.withPayload("Message").build());
    }

    @GetMapping("/sendObject")
    public void sendObject(){
        CartDTO cartDTO=new CartDTO("12345",1234);
        streamClient.output().send(MessageBuilder.withPayload(cartDTO).build());
    }
}
