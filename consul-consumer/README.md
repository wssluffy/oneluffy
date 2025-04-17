# Consul 服务消费者

## 项目说明
本项目是基于Spring Cloud Consul的服务消费者示例，实现了服务发现和负载均衡功能。通过Feign客户端实现对Consul服务提供者的调用。

## 技术栈
- Spring Boot
- Spring Cloud
- Spring Cloud Consul
- Spring Cloud OpenFeign
- Lombok

## 项目结构
```
consul-consumer
├── src/main/java
│   └── com.oneluffy.consul
│       ├── ConsulConsumerApplication.java    // 启动类
│       ├── controller                        // 控制器层
│       │   └── ConsumerController.java       // 消费者控制器
│       └── feign                            // Feign客户端
│           └── ConsulServerClient.java       // 服务调用接口
└── pom.xml                                  // 项目依赖
```

## 核心功能
1. **服务发现**
   - 通过`@EnableDiscoveryClient`注解启用服务发现
   - 自动注册到Consul注册中心

2. **负载均衡**
   - 使用Feign集成的Ribbon实现负载均衡
   - 默认使用轮询策略
   - 自动在多个服务实例间分发请求

3. **服务调用**
   - 通过Feign声明式接口调用服务
   - 支持REST风格的API调用

## 配置说明
主要依赖版本：
- Spring Cloud: 2023.0.0
- Java: 17

## 使用方法
1. **环境准备**
   - 安装并启动Consul服务
   - 确保consul-server服务已启动并注册到Consul
   - 第一种启动方法：consul agent -dev 默认启动本地127.0.0.1:8500
     另一种启动方式 使用 ipconfig ：查看自己本地的ipv4地址
     使用：consul.exe agent -dev -client 自己本地ipv4地址 启动consul
     用自己的本地ip地址+8500访问网站

2. **启动服务**
   ```bash
   mvn spring-boot:run
   ```

3. **接口测试**
   - 访问测试接口：`http://localhost:端口/consumer/hello`
   - 观察负载均衡效果：多次访问会轮询到不同的服务提供者实例

## 注意事项
1. 确保Consul服务正常运行
2. 确保至少有一个consul-server服务实例可用
3. 检查服务端口是否被占用

## 开发建议
1. 建议在开发环境中使用Consul的Dev模式启动
2. 可以通过Consul UI界面查看服务注册情况
3. 建议使用Postman或类似工具测试接口

## 问题排查
如果遇到服务调用失败，请检查：
1. Consul服务是否正常运行
2. 服务提供者是否已注册到Consul
3. 网络连接是否正常
4. 服务名称是否配置正确 