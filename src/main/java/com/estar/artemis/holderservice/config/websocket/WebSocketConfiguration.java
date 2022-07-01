package com.estar.artemis.holderservice.config.websocket;

import com.estar.artemis.holderservice.handler.AppClientHandler;
import com.estar.artemis.holderservice.inteceptor.ClientTokenInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
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
public class WebSocketConfiguration implements WebSocketConfigurer, BeanFactoryAware {

    private AutowireCapableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (AutowireCapableBeanFactory) beanFactory;
    }

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(this.beanFactory.createBean(AppClientHandler.class), "/ws/do")
                .addInterceptors(this.beanFactory.createBean(ClientTokenInterceptor.class))
                .setAllowedOrigins("*");

    }

}
