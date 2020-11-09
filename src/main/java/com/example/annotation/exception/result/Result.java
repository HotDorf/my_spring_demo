package com.example.annotation.exception.result;

import lombok.Data;

import java.util.Date;

@Data
public class Result<T> {

    /**
     *  返回码
     */
    private String code;

    /**
     *  错误信息
     */
    private String message;

    /**
     *  细节信息
     */
    private String detailMessage;

    /**
     *  返回结果时间戳
     */
    private String resultTimestamp;

    /**
     *  返回内容
     */
    private T data;
}
