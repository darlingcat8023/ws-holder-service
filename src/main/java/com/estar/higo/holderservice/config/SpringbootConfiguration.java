package com.estar.higo.holderservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xiaowenrou
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SpringbootConfiguration {

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "message.topics")
    public static class MessageTopics {
        private String commandTopic;
    }


}
