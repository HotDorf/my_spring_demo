package com.example.annotation.exception;

import com.example.annotation.exception.xmlCodeMessge.ErrorCodeContainer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.Logger;

/**
 *  这是一个系统异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -5017472441382587102L;

    private String code;
    private String message;

    public SystemException(String code) {
        super(code + " : " + ErrorCodeContainer.getErrorMessage(code,""));
        this.code = code;
        this.message = ErrorCodeContainer.getErrorMessage(code,"");
    }

    public SystemException(String code, String... param) {
        super(code + " : " + ErrorCodeContainer.getErrorMessage(code,param));
        this.code = code;
        this.message = ErrorCodeContainer.getErrorMessage(code,param);
    }

    public <E extends Exception> SystemException(String code, Logger logger, E e) {
        super(code + " : " + ErrorCodeContainer.getErrorMessage(code,""));
        this.code = code;
        this.message = ErrorCodeContainer.getErrorMessage(code,"");
        logger.error(ExceptionHandler.getExceptionStack(e));
    }

    public <E extends Exception> SystemException(String code, Logger logger, E e, String... param) {
        super(code + " : " + ErrorCodeContainer.getErrorMessage(code,param));
        this.code = code;
        this.message = ErrorCodeContainer.getErrorMessage(code,param);
        logger.error(ExceptionHandler.getExceptionStack(e));
    }
}
