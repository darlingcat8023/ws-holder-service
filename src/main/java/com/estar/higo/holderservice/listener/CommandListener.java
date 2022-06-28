package com.estar.higo.holderservice.listener;

import com.estar.higo.holderservice.config.SpringbootConfiguration;
import com.estar.higo.holderservice.model.CommandMessage;
import com.estar.higo.holderservice.service.impl.command.CommandPushServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
@Slf4j
@Component
public class CommandListener extends AbstractMessageListener<CommandMessage> {

    private final SpringbootConfiguration.MessageTopics messageTopics;

    private final CommandPushServiceImpl commandPushService;

    public CommandListener(SpringbootConfiguration.MessageTopics messageTopics, CommandPushServiceImpl commandPushService) {
        super(new Jackson2JsonRedisSerializer<>(CommandMessage.class));
        this.messageTopics = messageTopics;
        this.commandPushService = commandPushService;
    }

    @Override
    public String topic() {
        return this.messageTopics.getCommandTopic();
    }

    @Override
    public void onMessage(CommandMessage message, String pattern) {
        log.info("message received : {}, pattern : {}", message, pattern);
        this.commandPushService.push(message);
    }

}
