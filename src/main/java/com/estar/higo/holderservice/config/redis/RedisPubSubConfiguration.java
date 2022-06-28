package com.estar.higo.holderservice.config.redis;

import com.estar.higo.holderservice.listener.RedisMessageListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
@Configuration(proxyBeanMethods = false)
public class RedisPubSubConfiguration {

    /**
     * Redis sub配置
     * @param applicationContext
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer redisContainer(ApplicationContext applicationContext, RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        applicationContext.getBeansOfType(RedisMessageListener.class)
                .forEach((name, listener) -> container.addMessageListener(listener, listener.messageTopic()));
        return container;
    }

}
