package com.estar.higo.holderservice.handler;

import com.estar.higo.holderservice.context.WebSessionHolder;
import com.estar.higo.holderservice.remote.ConnectedRequest;
import com.estar.higo.holderservice.remote.DisconnectedRequest;
import com.estar.higo.holderservice.remote.RemoteGameService;
import com.estar.higo.holderservice.utils.SocketSessionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import static com.estar.higo.holderservice.config.constants.ParamConstants.HEADER_FORWARDED;

/**
 * @author xiaowenrou
 * @data 2022/6/10
 */
@Slf4j
@AllArgsConstructor
public class AppClientHandler extends AbstractWebSocketHandler {

    private final RemoteGameService remoteGameService;

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        log.info("ws connection established id = {}", session.getId());
        this.remoteGameService.onConnected(SocketSessionUtils.getSessionAttribute(session, HEADER_FORWARDED, String.class), ConnectedRequest.builder().build());
        WebSessionHolder.register(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        String msg = message.getPayload();
        log.info("ws message received id = {}, msg = {}", session.getId(), msg);
        super.handleTextMessage(session, message);
    }

    @Override
    protected void handleBinaryMessage(@NonNull WebSocketSession session, @NonNull BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
    }

    @Override
    protected void handlePongMessage(@NonNull WebSocketSession session, @NonNull PongMessage message) throws Exception {
        log.info("ws pong message {}",  message);
        super.handlePongMessage(session, message);
    }

    @Override
    public void handleTransportError(@NonNull WebSocketSession session, @NonNull Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        log.info("ws connection closed id = {}", session.getId());
        WebSessionHolder.unregister(session);
        this.remoteGameService.onDisconnected(SocketSessionUtils.getSessionAttribute(session, HEADER_FORWARDED, String.class), DisconnectedRequest.builder().build());
        super.afterConnectionClosed(session, status);
    }

}
