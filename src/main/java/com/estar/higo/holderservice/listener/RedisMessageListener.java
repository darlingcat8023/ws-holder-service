package com.estar.higo.holderservice.listener;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
public interface RedisMessageListener extends MessageListener {

    /**
     * 订阅主题
     * @return
     */
    ChannelTopic messageTopic();

}
