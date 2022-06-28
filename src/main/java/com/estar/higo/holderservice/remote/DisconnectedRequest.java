package com.estar.higo.holderservice.remote;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.socket.CloseStatus;

/**
 * @author xiaowenrou
 * @data 2022/6/22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DisconnectedRequest extends ConnectedRequest {

    private CloseStatus closeStatus;

}
