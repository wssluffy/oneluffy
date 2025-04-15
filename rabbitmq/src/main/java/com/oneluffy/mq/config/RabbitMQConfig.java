package com.oneluffy.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RabbitMQConfig {

    // 定义交换机名称
    public static final String DIRECT_EXCHANGE = "oneluffy.direct";
    public static final String TOPIC_EXCHANGE = "oneluffy.topic";
    public static final String FANOUT_EXCHANGE = "oneluffy.fanout";
    
    // 定义队列名称
    public static final String DIRECT_QUEUE = "oneluffy.direct.queue";
    public static final String TOPIC_QUEUE_1 = "oneluffy.topic.queue1";
    public static final String TOPIC_QUEUE_2 = "oneluffy.topic.queue2";
    public static final String FANOUT_QUEUE_1 = "oneluffy.fanout.queue1";
    public static final String FANOUT_QUEUE_2 = "oneluffy.fanout.queue2";
    
    // 定义路由键
    public static final String DIRECT_ROUTING_KEY = "direct";
    public static final String TOPIC_ROUTING_KEY_1 = "topic.message";
    public static final String TOPIC_ROUTING_KEY_2 = "topic.#";

    // 创建直连交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    // 创建主题交换机
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    // 创建扇形交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    // 创建直连队列
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE);
    }

    // 创建主题队列1
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_1);
    }

    // 创建主题队列2
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2);
    }

    // 创建扇形队列1
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1);
    }

    // 创建扇形队列2
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2);
    }

    // 绑定直连交换机和队列
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_ROUTING_KEY);
    }

    // 绑定主题交换机和队列1
    @Bean
    public Binding bindingTopic1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(TOPIC_ROUTING_KEY_1);
    }

    // 绑定主题交换机和队列2
    @Bean
    public Binding bindingTopic2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(TOPIC_ROUTING_KEY_2);
    }

    // 绑定扇形交换机和队列1
    @Bean
    public Binding bindingFanout1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    // 绑定扇形交换机和队列2
    @Bean
    public Binding bindingFanout2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public MessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(Arrays.asList("java.util.*", "java.lang.*", "com.oneluffy.*"));
        return converter;
    }
} 