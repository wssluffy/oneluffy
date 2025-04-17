# RabbitMQ模块

本模块提供了基于Spring Boot的RabbitMQ实现，包含了三种类型的消息交换机和队列的配置与使用示例。

## 功能特性

- 支持直连交换机（Direct Exchange）
- 支持主题交换机（Topic Exchange）
- 支持扇形交换机（Fanout Exchange）
- 提供了消息生产者和消费者示例
- 包含REST API接口用于测试消息发送

## 项目结构

```
rabbitmq/
├── src/main/java/com/oneluffy/mq/
│   ├── config/
│   │   └── RabbitMQConfig.java        # RabbitMQ配置类
│   ├── producer/
│   │   └── MessageProducer.java       # 消息生产者
│   ├── consumer/
│   │   └── MessageConsumer.java       # 消息消费者
│   ├── controller/
│   │   └── MessageController.java     # 测试控制器
│   └── MqApplication.java             # 应用程序入口
└── src/main/resources/
    └── application.yml                # 应用配置文件
```

## 配置说明

RabbitMQ的配置位于`application.yml`文件中：

```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    virtual-host: /
```

## 交换机和队列

本模块定义了以下交换机和队列：

### 直连交换机（Direct Exchange）
- 交换机名称：oneluffy.direct
- 队列名称：oneluffy.direct.queue
- 路由键：direct

### 主题交换机（Topic Exchange）
- 交换机名称：oneluffy.topic
- 队列名称：oneluffy.topic.queue1, oneluffy.topic.queue2
- 路由键：topic.message, topic.#

### 扇形交换机（Fanout Exchange）
- 交换机名称：oneluffy.fanout
- 队列名称：oneluffy.fanout.queue1, oneluffy.fanout.queue2
- 不需要路由键

## API接口

### 发送直连消息
- 请求方式：GET
- URL：`/mq/direct`
- 参数：
  - message：消息内容（默认值：hello direct）
- 响应：直连消息发送成功

### 发送主题消息
- 请求方式：GET
- URL：`/mq/topic`
- 参数：
  - routingKey：路由键（默认值：topic.message）
  - message：消息内容（默认值：hello topic）
- 响应：主题消息发送成功，路由键: {routingKey}

### 发送扇形消息
- 请求方式：GET
- URL：`/mq/fanout`
- 参数：
  - message：消息内容（默认值：hello fanout）
- 响应：扇形消息发送成功

## 使用说明

1. 确保已安装并启动RabbitMQ服务
   进入目录RabbitMQ Server\rabbitmq_server-4.0.9\sbin，输入cmd命令：rabbitmq-plugins enable rabbitmq_management
   启动成功后，访问 http://127.0.0.1:15672/
2. 启动应用程序：`mvn spring-boot:run`
3. 通过浏览器或API工具访问接口，例如：
   - http://localhost:8080/mq/direct?message=测试直连消息
   - http://localhost:8080/mq/topic?routingKey=topic.message&message=测试主题消息
   - http://localhost:8080/mq/fanout?message=测试扇形消息
4. 观察控制台日志以查看消息接收情况

## 注意事项

- 在生产环境中，请确保修改默认的用户名和密码
- 可以根据实际需求调整交换机和队列的配置
- 队列消费者可以根据业务需求进行定制化开发 