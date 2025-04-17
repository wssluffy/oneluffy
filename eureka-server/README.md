# Spring Cloud Eureka 微服务示例

本项目是一个基于Spring Cloud Eureka的微服务示例，包含三个模块：

## 1. Eureka Server (服务注册中心)
- **端口**：8761
- **功能**：作为服务注册和发现中心
- **访问地址**：http://localhost:8761
- **Actuator地址**：http://localhost:8761/actuator

## 2. Eureka Provider (服务提供者)
- **端口**：8001
- **功能**：提供一个简单的Hello服务
- **API地址**：/hello?name=xxx
- **示例**：http://localhost:8001/hello?name=oneluffy
- **Actuator地址**：
  - 服务信息：http://localhost:8001/actuator/info
  - 健康状态：http://localhost:8001/actuator/health
  - 所有端点：http://localhost:8001/actuator

## 3. Eureka Consumer (服务消费者)
- **端口**：8002
- **功能**：通过Feign调用Provider提供的服务
- **API地址**：/consumer/hello?name=xxx
- **示例**：http://localhost:8002/consumer/hello?name=oneluffy
- **Actuator地址**：http://localhost:8002/actuator

## 使用方法
1. 首先启动 Eureka Server
2. 然后启动 Eureka Provider
3. 最后启动 Eureka Consumer

## 项目结构
```
oneluffy
├── eureka-server        # 服务注册中心
├── eureka-provider      # 服务提供者
└── eureka-consumer      # 服务消费者（使用Feign调用Provider）
```

## 技术栈
- Spring Boot 3.2.3
- Spring Cloud 2023.0.0
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Boot Actuator

## Actuator端点

所有服务都配置了以下Actuator端点：

| 端点        | 描述                 | URL示例                      |
|------------|---------------------|------------------------------|
| /info      | 应用信息             | http://localhost:8001/actuator/info |
| /health    | 健康状态             | http://localhost:8001/actuator/health |
| /metrics   | 指标信息             | http://localhost:8001/actuator/metrics |
| /env       | 环境变量             | http://localhost:8001/actuator/env |
| /beans     | Spring Bean列表      | http://localhost:8001/actuator/beans |
| /mappings  | 所有HTTP映射         | http://localhost:8001/actuator/mappings |

## 注意事项
- 确保先启动Eureka Server，再启动其他服务
- Provider和Consumer都需要向Eureka Server注册
- Consumer通过Feign客户端实现对Provider服务的调用
- 所有服务都配置了Actuator，可以通过/actuator访问

## 示例流程
1. 启动Eureka Server后，可以访问http://localhost:8761查看注册中心
2. 启动Provider后，在Eureka控制台可以看到Provider已注册
3. 启动Consumer后，在Eureka控制台可以看到Consumer已注册
4. 访问http://localhost:8002/consumer/hello?name=oneluffy测试服务调用
5. 访问http://localhost:8001/actuator/info查看Provider的信息 