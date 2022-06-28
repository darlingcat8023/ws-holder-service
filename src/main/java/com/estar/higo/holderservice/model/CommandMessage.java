package com.estar.higo.holderservice.model;

import com.estar.higo.holderservice.service.impl.command.GameCommand;
import lombok.Data;

/**
 * @author xiaowenrou
 * @data 2022/6/24
 */
@Data
public class CommandMessage implements GameCommand {

    private Integer pushType;

    private Integer priority;

    private String gameId;

    private String userId;

    private String command;

}
