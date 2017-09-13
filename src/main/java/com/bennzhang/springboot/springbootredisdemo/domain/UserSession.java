package com.bennzhang.springboot.springbootredisdemo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSession implements Serializable {
    /**
     * Solve this error
     * java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type
     */
    private static final long serialVersionUID = -1L;

    private String id;
    private String name;
    private String deviceId;
    private String userAgent;
    private String token;
    private String sessionId;
}
