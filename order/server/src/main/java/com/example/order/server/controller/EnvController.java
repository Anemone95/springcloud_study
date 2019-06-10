package com.example.order.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvController {
    @Value("${spring.application.name}")
    private String env;

    @GetMapping("/print")
    public String print(){
        return env;
    }
}
