package com.estar.higo.holderservice.listener;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
@Slf4j
@Setter
public abstract class AbstractMessageListener<T> implements RedisMessageListener, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private RedisSerializer<T> serializer;

    private RedisSerializer<String> stringRedisSerializer;

    public AbstractMessageListener(RedisSerializer<T> serializer) {
        Assert.notNull(serializer, "serializer must not be null");
        this.serializer = serializer;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.stringRedisSerializer == null) {
            this.setStringRedisSerializer(RedisSerializer.string());
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
