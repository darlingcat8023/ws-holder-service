package com.estar.higo.holderservice.service;

/**
 * @author xiaowenrou
 * @data 2022/6/24
 */
public interface PushService<T> {

    /**
     * 推送逻辑
     * @param object
     */
    void push(T object);

}
