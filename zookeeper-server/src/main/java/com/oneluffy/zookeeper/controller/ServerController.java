package com.oneluffy.zookeeper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供者控制器
 */
@RestController
public class ServerController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Zookeeper Server on port: " + serverPort;
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
} 