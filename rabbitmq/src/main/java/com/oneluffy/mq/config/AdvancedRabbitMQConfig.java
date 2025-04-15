package com.oneluffy.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvancedRabbitMQConfig {

    // 死信交换机
    public static final String DLX_EXCHANGE = "oneluffy.dlx";
    // 死信队列
    public static final String DLX_QUEUE = "oneluffy.dlx.queue";
    // 死信路由键
    public static final String DLX_ROUTING_KEY = "dlx";
    
    // 延迟队列
    public static final String DELAY_QUEUE = "oneluffy.delay.queue";
    // 延迟交换机
    public static final String DELAY_EXCHANGE = "oneluffy.delay";
    // 延迟路由键
    public static final String DELAY_ROUTING_KEY = "delay";

    // 创建死信交换机
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    // 创建死信队列
    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable(DLX_QUEUE).build();
    }

    // 绑定死信交换机和队列
    @Bean
    public Binding bindingDlx() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(DLX_ROUTING_KEY);
    }

    // 创建延迟队列
    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable(DELAY_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)  // 设置死信交换机
                .withArgument("x-dead-letter-routing-key", DLX_ROUTING_KEY)  // 设置死信路由键
                .withArgument("x-message-ttl", 10000)  // 设置消息过期时间，单位毫秒
                .build();
    }

    // 创建延迟交换机
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE);
    }

    // 绑定延迟交换机和队列
    @Bean
    public Binding bindingDelay() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }
} 