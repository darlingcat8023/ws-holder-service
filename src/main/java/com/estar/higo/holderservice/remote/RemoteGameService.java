package com.estar.higo.holderservice.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.estar.higo.holderservice.config.constants.ParamConstants.HEADER_FORWARDED;

/**
 * @author xiaowenrou
 * @data 2022/6/22
 */
@FeignClient(value = "higo-gateway-service", url = "${service.gateway.url}")
public interface RemoteGameService {

    /**
     * ws连接建立之后的消息
     * @param forward
     * @param request
     */
    @PostMapping(value = "/api/connected")
    void onConnected(@RequestHeader(value = HEADER_FORWARDED) String forward, @RequestBody ConnectedRequest request);

    /**
     * ws发出指令
     * @param forward
     * @param request
     */
    @PostMapping(value = "/api/command")
    void onCommand(@RequestHeader(value = HEADER_FORWARDED) String forward, @RequestBody CommandRequest request);

    /**
     * ws断开连接之后的消息
     * @param forward
     * @param request
     */
    @PostMapping(value = "/api/disconnected")
    void onDisconnected(@RequestHeader(value = HEADER_FORWARDED) String forward, @RequestBody ConnectedRequest request);

}
