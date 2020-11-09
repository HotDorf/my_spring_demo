package com.example.annotation.exception.result;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class ResultUtils<T> {

    private static final String SUCCESS_CODE = "0000";
    private static final String DEFAULT_MESSAGE = "success";
    private static final String FAIL_CODE = "9999";


    private Result<T> result;

    public ResultUtils() {
        this.result.setCode(SUCCESS_CODE);
        this.result.setMessage(DEFAULT_MESSAGE);
        this.result.setResultTimestamp(DateFormatUtils.format(new Date(),"yyyy:MM:dd HH:mm:ss"));
    }

    private Result<T> setData(T t){
        result.setData(t);
        return result;
    }

    private Result<T> setErrorMsg(String msg,String detail){
        this.result.setCode(FAIL_CODE);
        this.result.setMessage(msg);
        this.result.setDetailMessage(detail);
        this.result.setResultTimestamp(DateFormatUtils.format(new Date(),"yyyy:MM:dd HH:mm:ss"));
        return this.result;
    }

    public static <T> Result<T> success(T t) {
        return   new ResultUtils<T>().setData(t);
    }

    public static <T> Result<T> error(String msg,String detail) {
        return   new ResultUtils<T>().setErrorMsg(msg,detail);
    }
}
