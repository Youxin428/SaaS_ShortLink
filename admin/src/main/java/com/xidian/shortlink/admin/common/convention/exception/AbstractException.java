package com.xidian.shortlink.admin.common.convention.exception;

import com.xidian.shortlink.admin.common.convention.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 抽象三类异常
 * @see ClientException 客户端异常
 * @see ServiceException 服务端异常
 * @see RemoteException 远程调用异常
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(IErrorCode errorCode, String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(errorMessage) ? errorMessage : null)
                .orElse(errorCode.message());
    }
}
