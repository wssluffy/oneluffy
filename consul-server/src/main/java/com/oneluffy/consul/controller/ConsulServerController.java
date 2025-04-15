package com.oneluffy.consul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oneluffy
 * @date 2025/4/15 14:40
 */
@RestController
@RequestMapping("/consul")
public class ConsulServerController {

    @RequestMapping("/server")
    public String server() {
        return "consul server";
    }
}
