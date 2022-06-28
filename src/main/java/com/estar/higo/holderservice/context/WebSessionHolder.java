package com.estar.higo.holderservice.context;

import com.estar.higo.holderservice.utils.SocketSessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.estar.higo.holderservice.config.constants.ParamConstants.PARAM_USER_ID;

/**
 * @author xiaowenrou
 * @data 2022/6/13
 */
@Slf4j
public abstract class WebSessionHolder {

    /**
     * SessionId -> Session
     */
    private static final Map<String, WebSocketSession> sessionIdMap;

    /**
     * GameId -> [SessionId1, SessionId2, ...]
     */
    private static final Map<String, Map<String, WebSocketSession>> gameIdMap;

    /**
     * UserId -> Session
     */
    private static final Map<String, WebSocketSession> userIdMap;

    static {
        sessionIdMap = new ConcurrentHashMap<>(1 << 5);
        gameIdMap = new ConcurrentHashMap<>(1 << 5);
        userIdMap = new ConcurrentHashMap<>(1 << 5);
    }

    /**
     * 注册ws连接信息
     * @param session
     */
    public static void register(WebSocketSession session) {
        WebSessionHolder.sessionIdMap.put(session.getId(), session);
        var userId = SocketSessionUtils.getSessionAttribute(session, PARAM_USER_ID, String.class);
        WebSessionHolder.userIdMap.put(userId, session);
        var gameId = SocketSessionUtils.getSessionAttribute(session, PARAM_USER_ID, String.class);
        WebSessionHolder.gameIdMap.computeIfAbsent(gameId, key -> new ConcurrentHashMap<>(1 << 5)).put(session.getId(), session);
    }

    /**
     * 释放ws连接信息
     * @param session
     */
    public static void unregister(WebSocketSession session) {
        if (!WebSessionHolder.sessionIdMap.containsKey(session.getId())) {
            return;
        }
        if (WebSessionHolder.remove(WebSessionHolder.sessionIdMap.remove(session.getId())) != session) {
            log.warn("can not remove session id from sessionIdMap = {}", session.getId());
        }
    }

    /**
     * 下线指定的session
     * @param sessionId
     */
    public static void offline(String sessionId) {
        if (!WebSessionHolder.sessionIdMap.containsKey(sessionId)) {
            return;
        }
        WebSessionHolder.remove(WebSessionHolder.sessionIdMap.remove(sessionId));
    }

    private static WebSocketSession remove(WebSocketSession session) {
        var userId = SocketSessionUtils.getSessionAttribute(session, PARAM_USER_ID, String.class);
        if (WebSessionHolder.userIdMap.remove(userId) != session) {
            log.warn("can not remove session id from userIdMap = {}", session.getId());
        }
        var gameId = SocketSessionUtils.getSessionAttribute(session, PARAM_USER_ID, String.class);
        if (WebSessionHolder.gameIdMap.get(gameId).remove(session.getId()) != session) {
            log.warn("can not remove session id from gameIdMap = {}", session.getId());
        }
        return session;
    }


}
