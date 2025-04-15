package com.oneluffy.mq.producer;

import com.oneluffy.mq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到直连交换机
     * 
     * @param message 消息内容
     */
    public void sendDirectMessage(Object message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY, message);
    }

    /**
     * 发送消息到主题交换机
     * 
     * @param routingKey 路由键
     * @param message 消息内容
     */
    public void sendTopicMessage(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, routingKey, message);
    }

    /**
     * 发送消息到扇形交换机
     * 
     * @param message 消息内容
     */
    public void sendFanoutMessage(Object message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message);
    }
} 