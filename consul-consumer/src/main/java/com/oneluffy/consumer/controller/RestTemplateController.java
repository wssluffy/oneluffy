package com.oneluffy.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {

    private final RestTemplate restTemplate;
    
    // consul-server 服务的名称
    private static final String SERVICE_NAME = "consul-server";

    public RestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test")
    public String test() {
        // 使用服务名称进行调用
        String url = "http://" + SERVICE_NAME + "/consul/server";
        return restTemplate.getForObject(url, String.class);
    }
} 