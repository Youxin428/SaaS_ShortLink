package com.xidian.shortlink.admin.common.convention.exception;

import com.xidian.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.xidian.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * 客户端异常
 */
public class ClientException extends AbstractException{

    public ClientException(IErrorCode errorCode) {
        super(errorCode, null, null);
    }

    public ClientException(String message) {
        super(BaseErrorCode.CLIENT_ERROR, message, null);
    }

    public ClientException(IErrorCode errorCode, String message) {
        super(errorCode, message, null);
    }

    public ClientException(IErrorCode errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
