package com.estar.artemis.holderservice.listener;

import com.estar.artemis.holderservice.config.SpringbootConfiguration;
import com.estar.artemis.holderservice.model.CommandMessage;
import com.estar.artemis.holderservice.service.impl.command.CommandPushServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
@Slf4j
@Component
public class CommandListener extends AbstractMessageListener<CommandMessage> {

    @Autowired
    private SpringbootConfiguration.MessageTopics messageTopics;

    @Autowired
    private CommandPushServiceImpl commandPushService;

    public CommandListener() {
        super(CommandMessage.class);
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
