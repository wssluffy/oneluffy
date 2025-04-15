package com.oneluffy.consumer.controller;

import com.oneluffy.consumer.feign.ConsulServerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    private final ConsulServerClient consulServerClient;

    public TestController(ConsulServerClient consulServerClient) {
        this.consulServerClient = consulServerClient;
    }

    @GetMapping("/consumer/test")
    public String test() {
        return consulServerClient.test();
    }
} 