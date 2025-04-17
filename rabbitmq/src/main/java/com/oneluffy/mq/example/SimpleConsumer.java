package com.oneluffy.mq.example;

import com.oneluffy.mq.config.RabbitMQConfig;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @date 2025/4/17 17:31
 */
public class SimpleConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = SimpleProducer.getConnection();

        // 创建信道
        final Channel channel = connection.createChannel();
        channel.basicQos(64); // 设置客户端最多接收未被 ack 的消息数量为 64 。

        // 订阅消费 QUEUE_NAME 队列
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                // 打印日志
                System.out.printf("[线程：%s][路由键：%s][消息内容：%s]%n",
                        Thread.currentThread(), envelope.getRoutingKey(), message);
                // ack 手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(RabbitMQConfig.DIRECT_QUEUE, consumer);

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        channel.close();
        connection.close();
    }
}
