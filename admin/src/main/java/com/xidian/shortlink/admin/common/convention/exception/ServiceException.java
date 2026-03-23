package com.xidian.shortlink.admin.common.convention.exception;

import com.xidian.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.xidian.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * 服务端异常
 */
public class ServiceException extends AbstractException {

    public ServiceException(IErrorCode errorCode) {
        super(errorCode, null, null);
    }

    public ServiceException(String message) {
        super(BaseErrorCode.SERVICE_ERROR, message, null);
    }

    public ServiceException(IErrorCode errorCode, String message) {
        super(errorCode, message, null);
    }

    public ServiceException(IErrorCode errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
