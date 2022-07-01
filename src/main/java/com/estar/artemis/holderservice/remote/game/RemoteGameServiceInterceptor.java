package com.estar.artemis.holderservice.remote.game;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import static com.estar.artemis.holderservice.config.constants.ParamConstants.HEADER_FORWARDED;

/**
 * @author xiaowenrou
 * @data 2022/7/1
 */
@Slf4j
public class RemoteGameServiceInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var target = requestTemplate.headers().get(HEADER_FORWARDED).iterator().next();
        if (target.startsWith("http://")) {
            target = "http://".concat(target);
        }
        requestTemplate.target(target);
    }

}
