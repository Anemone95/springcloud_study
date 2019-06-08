package com.example.order.controller;

import com.example.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg4")
    public String getProductMsg4(){
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductMsg3")
    public String getProductMsg3() {
        String response=restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductMsg2")
    public String getProductMsg2() {
        ServiceInstance serviceInstance=loadBalancerClient.choose("PRODUCT");
        String url=String.format("http://%s:%s/msg", serviceInstance.getHost(), serviceInstance.getPort());
        log.info("product url={}",url);
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductMsg1")
    public String getProductMsg1() {
        // RestTemplate way 1
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("response={}", response);
        return response;
    }
}
