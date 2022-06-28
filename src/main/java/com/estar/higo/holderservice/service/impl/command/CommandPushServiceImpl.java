package com.estar.higo.holderservice.service.impl.command;

import com.estar.higo.holderservice.service.PushService;
import org.springframework.stereotype.Component;

/**
 * @author xiaowenrou
 * @data 2022/6/24
 */
@Component
public class CommandPushServiceImpl implements PushService<GameCommand> {

    @Override
    public void push(GameCommand object) {
        // TODO
    }

}
