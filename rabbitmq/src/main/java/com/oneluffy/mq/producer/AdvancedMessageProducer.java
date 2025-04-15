package com.oneluffy.mq.producer;

import com.oneluffy.mq.config.AdvancedRabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdvancedMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送延迟消息
     */
    public void sendDelayMessage(String message, int delayTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("time", LocalDateTime.now().toString());
        map.put("delayTime", delayTime);

        Message msg = MessageBuilder.withBody(map.toString().getBytes())
                .setExpiration(String.valueOf(delayTime))
                .build();

        rabbitTemplate.convertAndSend(AdvancedRabbitMQConfig.DELAY_EXCHANGE, 
                                    AdvancedRabbitMQConfig.DELAY_ROUTING_KEY, 
                                    msg);
    }

    /**
     * 发送需要确认的消息
     */
    public void sendConfirmMessage(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("time", LocalDateTime.now().toString());
        map.put("needConfirm", true);

        rabbitTemplate.convertAndSend(AdvancedRabbitMQConfig.DELAY_EXCHANGE, 
                                    AdvancedRabbitMQConfig.DELAY_ROUTING_KEY, 
                                    map);
    }

    /**
     * 发送需要重试的消息
     */
    public void sendRetryMessage(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("time", LocalDateTime.now().toString());
        map.put("retryCount", 0);

        rabbitTemplate.convertAndSend(AdvancedRabbitMQConfig.DELAY_EXCHANGE, 
                                    AdvancedRabbitMQConfig.DELAY_ROUTING_KEY, 
                                    map);
    }
} 