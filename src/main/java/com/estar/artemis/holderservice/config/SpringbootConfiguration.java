package com.estar.artemis.holderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author xiaowenrou
 */
@EnableConfigurationProperties
@Configuration(proxyBeanMethods = false)
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SpringbootConfiguration {

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.createXmlMapper(false)
                .failOnEmptyBeans(false)
                .failOnUnknownProperties(false)
                .build();
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "message.topics")
    public static class MessageTopics {
        private String commandTopic;
    }

}
