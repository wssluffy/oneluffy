# OneluFFy 微服务示例项目

这是一个基于Spring Cloud的微服务示例项目，展示了微服务架构中的各种常见组件和最佳实践。

## 项目结构

```
oneluffy/
├── eureka-server/       # 服务注册中心
├── eureka-provider/     # 服务提供者
├── eureka-consumer/     # 服务消费者
├── consul-server/       # Consul服务示例
├── consul-consumer/     # Consul消费者示例
├── rabbitmq/           # RabbitMQ消息队列示例
├── user/               # 用户服务
└── zt/                 # 其他工具和示例
```

## 技术栈

- Spring Boot 3.2.3
- Spring Cloud 2023.0.0
- Spring Cloud LoadBalancer
- Spring Cloud OpenFeign
- Spring Cloud Netflix Eureka
- Consul
- RabbitMQ

## 主要功能

1. 服务注册与发现 (Eureka/Consul)
2. 负载均衡 (Spring Cloud LoadBalancer)
3. 声明式REST客户端 (OpenFeign)
4. 消息队列 (RabbitMQ)
5. 监控与管理 (Spring Boot Actuator)

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- RabbitMQ 3.8+

### 启动顺序

1. 启动eureka-server
```bash
cd eureka-server
mvn spring-boot:run
```

2. 启动eureka-provider
```bash
cd eureka-provider
mvn spring-boot:run
```

3. 启动eureka-consumer
```bash
cd eureka-consumer
mvn spring-boot:run
```

### 测试服务

1. 查看Eureka控制台
```
http://localhost:8761
```

2. 测试服务调用
```
# 直接调用Provider
http://localhost:8001/provider/hello

# 通过Consumer调用
http://localhost:8002/consumer/hello
```

3. 测试负载均衡
```
# 查看负载均衡配置
http://localhost:8002/consumer/lb-config

# 测试Feign负载均衡
http://localhost:8002/consumer/feign-lb

# 测试直接负载均衡
http://localhost:8002/consumer/ribbon-lb
```

## 负载均衡配置说明

本项目使用Spring Cloud LoadBalancer作为负载均衡组件，支持以下特性：

1. 默认使用轮询（Round Robin）策略
2. 支持自定义负载均衡策略
3. 与Feign完全集成

### 自定义负载均衡配置

在`eureka-consumer`模块中：

1. 配置类：`CustomLoadBalancerConfiguration.java`
2. 使用方式：通过`@LoadBalancerClient`注解指定

## 监控端点

所有服务都启用了Spring Boot Actuator，提供以下端点：

- `/actuator/health` - 健康检查
- `/actuator/info` - 应用信息
- `/actuator/metrics` - 指标信息

## 文档

详细文档请参见`docs`目录：

- [架构设计](docs/architecture.md)
- [API文档](docs/api.md)
- [部署指南](docs/deployment.md)

## 贡献指南

欢迎提交Issue和Pull Request。

## 许可证

本项目采用MIT许可证。

