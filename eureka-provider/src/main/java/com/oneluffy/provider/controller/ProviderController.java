package com.oneluffy.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;
    
    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Hello " + name + ", from Provider! Port: " + serverPort;
    }
    
    @GetMapping("/instance-info")
    public Map<String, Object> instanceInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("applicationName", applicationName);
        info.put("port", serverPort);
        info.put("timestamp", System.currentTimeMillis());
        return info;
    }
} 