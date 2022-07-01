package com.estar.artemis.holderservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
@Slf4j
public abstract class AbstractMessageListener<T> implements RedisMessageListener, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private RedisSerializer<T> serializer;

    private RedisSerializer<String> stringRedisSerializer;

    private final Class<T> type;

    public AbstractMessageListener(Class<T> type) {
        this.type = type;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.serializer == null) {
            Jackson2JsonRedisSerializer<T> redisSerializer = new Jackson2JsonRedisSerializer<>(this.type);
            redisSerializer.setObjectMapper(this.applicationContext.getBean(ObjectMapper.class));
            this.serializer = redisSerializer;
        }
        if (this.stringRedisSerializer == null) {
            this.stringRedisSerializer = this.applicationContext.getBean(StringRedisSerializer.class);
        }
    }

    @Override
    public ChannelTopic messageTopic() {
        return new ChannelTopic(this.topic());
    }

    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        try {
            T msg = this.serializer.deserialize(message.getBody());
            String channel = this.stringRedisSerializer.deserialize(pattern);
            this.onMessage(msg, channel);
        } catch (Exception ex) {
            log.error("Listener execution failed", ex);
        }
    }

    /**
     * 订阅主题
     * @return
     */
    public abstract String topic();

    /**
     * 序列化之后的消息
     * @param message
     * @param pattern
     */
    public abstract void onMessage(T message, String pattern);

}
