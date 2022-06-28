package com.estar.higo.holderservice.service;

/**
 * @author xiaowenrou
 * @data 2022/6/27
 */
public interface BaseCommand {

    /**
     * 推送类型：0 组推，1 单推
     * @return
     */
    Integer getPushType();

    /**
     * 优先级
     * @return
     */
    Integer getPriority();

}
