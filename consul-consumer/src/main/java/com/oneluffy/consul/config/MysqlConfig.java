package com.oneluffy.consul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "mysql")
public class MysqlConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
} 