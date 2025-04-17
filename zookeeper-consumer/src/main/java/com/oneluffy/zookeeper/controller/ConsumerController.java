package com.oneluffy.zookeeper.controller;

import com.oneluffy.zookeeper.feign.ZookeeperServerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务消费者控制器
 */
@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {
    
    private final ZookeeperServerClient zookeeperServerClient;

    @GetMapping("/hello")
    public String hello() {
        return zookeeperServerClient.hello();
    }

    @GetMapping("/health")
    public String health() {
        return zookeeperServerClient.health();
    }
} 