package com.oneluffy.zookeeper.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Zookeeper服务提供者客户端
 */
@FeignClient(name = "zookeeper-server")
public interface ZookeeperServerClient {
    
    @GetMapping("/hello")
    String hello();
    
    @GetMapping("/health")
    String health();
} 