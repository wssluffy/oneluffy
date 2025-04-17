package com.oneluffy.provider.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        details.put("serviceType", "provider");
        details.put("company", "oneluffy");
        details.put("status", "running");
        
        builder.withDetail("serviceDetails", details);
    }
} 