package com.estar.higo.holderservice.remote;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaowenrou
 * @data 2022/6/23
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CommandRequest extends ConnectedRequest {

    private Integer priority;

    private String command;

}
