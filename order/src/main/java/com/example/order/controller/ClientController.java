package com.example.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {
    public String getProductMsg() {
        // RestTemplate way
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("response={}", response);
        return response;
    }

}
