package com.oneluffy.mq.controller;

import com.oneluffy.mq.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mq")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    /**
     * 发送直连消息
     */
    @GetMapping("/direct")
    public String sendDirectMessage(@RequestParam(value = "message", defaultValue = "hello direct") String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("time", LocalDateTime.now().toString());
        
        messageProducer.sendDirectMessage(map);
        return "直连消息发送成功";
    }

    /**
     * 发送主题消息
     */
    @GetMapping("/topic")
    public String sendTopicMessage(
            @RequestParam(value = "routingKey", defaultValue = "topic.message") String routingKey,
            @RequestParam(value = "message", defaultValue = "hello topic") String message) {
        
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("routingKey", routingKey);
        map.put("time", LocalDateTime.now().toString());
        
        messageProducer.sendTopicMessage(routingKey, map);
        return "主题消息发送成功，路由键: " + routingKey;
    }

    /**
     * 发送扇形消息
     */
    @GetMapping("/fanout")
    public String sendFanoutMessage(@RequestParam(value = "message", defaultValue = "hello fanout") String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("time", LocalDateTime.now().toString());
        
        messageProducer.sendFanoutMessage(map);
        return "扇形消息发送成功";
    }
} 