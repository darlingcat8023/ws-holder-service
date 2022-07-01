package com.estar.artemis.holderservice.utils;

import org.springframework.web.socket.WebSocketSession;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
public abstract class SocketSessionUtils {

    public static <T> T getSessionAttribute(WebSocketSession session, String key, Class<T> clazz) {
        var attributes = session.getAttributes();
        if (clazz.isAssignableFrom(attributes.getClass())) {
            return (T) attributes.get(key);
        }
        throw new IllegalArgumentException(key + " type not match");
    }
}
