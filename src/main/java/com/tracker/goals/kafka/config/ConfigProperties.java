package com.tracker.goals.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.HashMap;
import java.util.Map;

//@ConfigurationProperties(prefix = "kafka")
public class ConfigProperties {

    private String kafkaServers;
    private String groupId;
    private String autoOffset;
    private String mechanism;
    private String username;
    private String password;
    private String protocol;

//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//
//    }

}
