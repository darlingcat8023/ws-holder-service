package com.estar.higo.holderservice.config.websocket;

import com.estar.higo.holderservice.handler.ClientHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author xiaowenrou
 * @data 2022/6/10
 */
@EnableWebSocket
@Configuration(proxyBeanMethods = false)
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(new ClientHandler(), "/ws/do");
    }

}
