package com.estar.higo.holderservice.remote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaowenrou
 * @data 2022/6/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectedRequest {

    private String gameId;

    private String userId;

    private String role;

    private String sessionId;

}
