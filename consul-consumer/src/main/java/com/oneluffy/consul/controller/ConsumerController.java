package com.oneluffy.consul.controller;

import com.oneluffy.consul.feign.ConsulServerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    private final ConsulServerClient consulServerClient;

    public ConsumerController(ConsulServerClient consulServerClient) {
        this.consulServerClient = consulServerClient;
    }

    @GetMapping("/consumer/test")
    public String test() {
        return consulServerClient.test();
    }
} 