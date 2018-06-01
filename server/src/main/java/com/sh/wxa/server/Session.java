package com.sh.wxa.server;

import lombok.Data;

@Data
public class Session {

    private volatile String sessionId;

}
