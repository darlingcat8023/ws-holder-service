package com.estar.artemis.holderservice.remote.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaowenrou
 * @data 2022/6/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectedRequest {

    private String gameId;

    private String userId;

    private String role;

    private String sessionId;

}
