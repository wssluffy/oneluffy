package com.oneluffy.consul.controller;

import com.oneluffy.consul.config.MysqlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigController {

    private final MysqlConfig mysqlConfig;

    public ConfigController(MysqlConfig mysqlConfig) {
        this.mysqlConfig = mysqlConfig;
    }

    @GetMapping("/mysql")
    public Map<String, Object> getMysqlConfig() {
        log.info("Current MySQL config: {}", mysqlConfig);
        Map<String, Object> config = new HashMap<>();
        config.put("url", mysqlConfig.getUrl());
        config.put("username", mysqlConfig.getUsername());
        config.put("password", mysqlConfig.getPassword());
        config.put("driverClassName", mysqlConfig.getDriverClassName());
        return config;
    }
} 