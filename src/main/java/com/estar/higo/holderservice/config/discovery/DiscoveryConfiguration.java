package com.estar.higo.holderservice.config.discovery;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaowenrou
 */
@Configuration(proxyBeanMethods = false)
@EnableDiscoveryClient(autoRegister = true)
public class DiscoveryConfiguration {
}
