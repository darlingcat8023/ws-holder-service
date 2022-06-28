package com.estar.higo.holderservice.config.feign;

import feign.Client;
import feign.Logger;
import feign.Request;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaowenrou
 * @data 2022/6/22
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(value = FeignClientsConfiguration.class)
@EnableFeignClients(basePackages = "com.estar.higo.holderservice.remote")
public class OpenFeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 响应info级别日志
     * @return
     */
    @Bean
    public Logger feignLogger() {
        return new FeignInfoLevelLogger();
    }

    /**
     * 超时时间配置
     * @return
     */
    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(
                3, TimeUnit.SECONDS,
                10, TimeUnit.SECONDS,
                true
        );
    }

    /**
     * 使用OkHttpClient
     * @param client
     * @return
     */
    @Bean
    public Client feignClient(OkHttpClient client) {
        return new feign.okhttp.OkHttpClient(client);
    }

    @Bean
    public ConnectionPool httpClientConnectionPool(FeignHttpClientProperties httpClientProperties, OkHttpClientConnectionPoolFactory connectionPoolFactory) {
        return connectionPoolFactory.create(15, 15, TimeUnit.MINUTES);
    }

    @Bean
    public OkHttpClient client(OkHttpClientFactory httpClientFactory, ConnectionPool connectionPool, FeignHttpClientProperties httpClientProperties) {
        return httpClientFactory.createBuilder(true)
                .connectTimeout(2L, TimeUnit.SECONDS)
                .followRedirects(true)
                .connectionPool(connectionPool)
                .build();
    }

}
