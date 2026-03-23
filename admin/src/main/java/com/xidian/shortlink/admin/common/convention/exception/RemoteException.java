package com.xidian.shortlink.admin.common.convention.exception;

import com.xidian.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.xidian.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * 远程服务调用异常
 */
public class RemoteException extends AbstractException{

    public RemoteException(IErrorCode errorCode) {
        super(errorCode, null, null);
    }

    public RemoteException(String message) {
        super(BaseErrorCode.REMOTE_ERROR, message, null);
    }

    public RemoteException(IErrorCode errorCode, String message) {
        super(errorCode, message, null);
    }

    public RemoteException(IErrorCode errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
