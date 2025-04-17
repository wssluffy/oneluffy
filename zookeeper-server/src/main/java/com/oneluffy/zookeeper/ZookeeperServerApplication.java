package com.oneluffy.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Zookeeper服务提供者启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServerApplication.class, args);
    }
} 