package com.example.annotation.exception;

import com.example.annotation.exception.result.Result;
import com.example.annotation.exception.result.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  这是一个对异常处理的增强类  @ControllerAdvice 通知增强
 */

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class ExceptionHandleAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String errorMsg = "MethodArgumentNotValidException";
        if (e != null){
            errorMsg = e.getMessage();
            log.error(e.toString(),e);
        }
        return ResultUtils.error("参数校验不通过！", org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e));
    }

    /**
     *  加了这个之后所有的异常都会是 200 OK
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(Exception e){
        String errorMsg = "Exception";
        if (e != null){
            errorMsg = e.getMessage();
            log.error(e.toString(),e);
        }
        return ResultUtils.error(errorMsg, org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e));
    }

    @ExceptionHandler(BussinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(BussinessException e){
        String errorMsg = "BussinessException";
        if (e != null){
            errorMsg = e.getMessage();
            log.error(e.toString(),e);
        }
        return ResultUtils.error(errorMsg, org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e));
    }
}
