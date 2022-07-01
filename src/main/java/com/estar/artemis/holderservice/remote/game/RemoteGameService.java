package com.estar.artemis.holderservice.remote.game;

import com.estar.artemis.holderservice.config.constants.ParamConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author xiaowenrou
 * @data 2022/6/22
 */
@FeignClient(value = "higo-game-service", url = "127.0.0.1:8080", configuration = {RemoteGameServiceInterceptor.class})
public interface RemoteGameService {

    /**
     * ws连接建立之后的消息
     * @param forward
     * @param request
     */
    @PostMapping(value = "/api/connected")
    void onConnected(@RequestHeader(value = ParamConstants.HEADER_FORWARDED) String forward, @RequestBody ConnectedRequest request);

    /**
     * ws发出指令
     * @param forward
     * @param request
     */
    @PostMapping(value = "/api/command")
    void onCommand(@RequestHeader(value = ParamConstants.HEADER_FORWARDED) String forward, @RequestBody CommandRequest request);

    /**
     * ws断开连接之后的消息
     * @param forward
     * @param request
     */
    @PostMapping(value = "/api/disconnected")
    void onDisconnected(@RequestHeader(value = ParamConstants.HEADER_FORWARDED) String forward, @RequestBody DisconnectedRequest request);

}
