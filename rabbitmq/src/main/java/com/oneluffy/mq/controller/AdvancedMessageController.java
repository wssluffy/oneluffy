package com.oneluffy.mq.controller;

import com.oneluffy.mq.producer.AdvancedMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq/advanced")
public class AdvancedMessageController {

    @Autowired
    private AdvancedMessageProducer advancedMessageProducer;

    /**
     * 发送延迟消息
     * @param message 消息内容
     * @param delayTime 延迟时间（毫秒）
     */
    @PostMapping("/delay")
    public String sendDelayMessage(@RequestParam String message, @RequestParam int delayTime) {
        advancedMessageProducer.sendDelayMessage(message, delayTime);
        return "延迟消息发送成功，延迟时间：" + delayTime + "毫秒";
    }

    /**
     * 发送需要确认的消息
     * @param message 消息内容
     */
    @PostMapping("/confirm")
    public String sendConfirmMessage(@RequestParam String message) {
        advancedMessageProducer.sendConfirmMessage(message);
        return "需要确认的消息发送成功";
    }

    /**
     * 发送需要重试的消息
     * @param message 消息内容
     */
    @PostMapping("/retry")
    public String sendRetryMessage(@RequestParam String message) {
        advancedMessageProducer.sendRetryMessage(message);
        return "需要重试的消息发送成功";
    }
} 