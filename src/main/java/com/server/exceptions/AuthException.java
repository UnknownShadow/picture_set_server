package com.server.exceptions;

import java.io.Serializable;

/**
 * Created by guange on 03/05/2017.
 */
public class AuthException extends RuntimeException implements Serializable {

    public static final int PasswordError = 1;
    public static final int UnActive = 2;

    /**
     * 异常编码 1. 用户名或密码不正确  2. 用户未激活或被禁用
     */
    private int errorCode;


    public AuthException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


}
