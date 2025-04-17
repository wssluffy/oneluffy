# Zookeeper 服务消费者

## 项目说明
本项目是基于Spring Cloud Zookeeper的服务消费者示例，通过Feign客户端调用服务提供者，并实现了负载均衡功能。

## 技术栈
- Spring Boot
- Spring Cloud
- Spring Cloud Zookeeper
- Spring Cloud OpenFeign
- Spring Cloud LoadBalancer
- Spring Boot Actuator
- Lombok

## 项目结构
```
zookeeper-consumer
├── src/main/java
│   └── com.oneluffy.zookeeper
│       ├── ZookeeperConsumerApplication.java    // 启动类
│       ├── controller                           // 控制器层
│       │   └── ConsumerController.java          // 消费者控制器
│       └── feign                               // Feign客户端
│           └── ZookeeperServerClient.java       // 服务调用接口
└── pom.xml                                     // 项目依赖
```

## 核心功能
1. **服务发现**
   - 通过`@EnableDiscoveryClient`注解启用服务发现
   - 自动从Zookeeper获取服务列表
   - 支持服务健康检查

2. **负载均衡**
   - 使用Spring Cloud LoadBalancer
   - 默认使用轮询策略
   - 支持重试机制
   - 自动剔除不健康实例

3. **服务调用**
   - 通过Feign声明式接口调用服务
   - 支持REST风格的API调用
   - 集成负载均衡功能

## 配置说明
主要配置项：
```yaml
spring:
  cloud:
    zookeeper:
      connect-string: localhost:2181    # Zookeeper连接地址
      discovery:
        enabled: true                   # 启用服务发现
    loadbalancer:
      retry:
        enabled: true                   # 启用重试机制
        max-retries-on-same-service-instance: 1    # 对同一实例重试次数
        max-retries-on-next-service-instance: 2    # 更换实例重试次数
```

## 使用方法
1. **环境准备**
   - 安装并启动Zookeeper服务
   - 确保服务提供者已启动并注册
   - 确保Java 17环境已安装

2. **启动服务**
   ```bash
   mvn spring-boot:run
   ```

3. **接口测试**
   - 消费者接口：`http://localhost:8082/consumer/hello`
   - 健康检查：`http://localhost:8082/consumer/health`
   - Actuator监控：`http://localhost:8082/actuator`

## 负载均衡测试
1. 启动多个服务提供者实例（不同端口）
2. 多次访问消费者接口
3. 观察返回结果中的端口号变化

## 注意事项
1. 确保Zookeeper服务正常运行
2. 确保至少有一个服务提供者可用
3. 检查服务端口是否被占用
4. 注意重试配置的合理性

## 开发建议
1. 使用Actuator监控服务状态
2. 合理配置重试机制，避免重试风暴
3. 建议使用Postman等工具测试接口
4. 关注服务调用的性能和超时设置

## 问题排查
如果遇到服务调用失败，请检查：
1. Zookeeper服务是否正常运行
2. 服务提供者是否可用
3. 网络连接是否正常
4. 负载均衡配置是否正确
5. 重试配置是否合理 