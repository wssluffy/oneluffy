package com.oneluffy.mq.consumer;

import com.oneluffy.mq.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    /**
     * 监听直连队列
     */
    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE)
    public void receiveDirectMessage(Map<String, Object> message) {
        logger.info("直连队列接收到消息: {}", message);
    }

    /**
     * 监听主题队列1
     */
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_1)
    public void receiveTopicMessage1(Map<String, Object> message) {
        logger.info("主题队列1接收到消息: {}", message);
    }

    /**
     * 监听主题队列2
     */
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_2)
    public void receiveTopicMessage2(Map<String, Object> message) {
        logger.info("主题队列2接收到消息: {}", message);
    }

    /**
     * 监听扇形队列1
     */
    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_1)
    public void receiveFanoutMessage1(Map<String, Object> message) {
        logger.info("扇形队列1接收到消息: {}", message);
    }

    /**
     * 监听扇形队列2
     */
    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_2)
    public void receiveFanoutMessage2(Map<String, Object> message) {
        logger.info("扇形队列2接收到消息: {}", message);
    }
} 