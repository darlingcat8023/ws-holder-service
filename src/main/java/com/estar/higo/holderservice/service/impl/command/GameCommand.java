package com.estar.higo.holderservice.service.impl.command;

import com.estar.higo.holderservice.service.BaseCommand;

/**
 * @author xiaowenrou
 * @data 2022/6/24
 */
public interface GameCommand extends BaseCommand {

    /**
     * 游戏id
     * @return
     */
    String getGameId();

    /**
     * 用户id
     * @return
     */
    String getUserId();

    /**
     * 命令
     * @return
     */
    String getCommand();

}
