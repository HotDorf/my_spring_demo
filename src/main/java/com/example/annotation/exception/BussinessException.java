package com.example.annotation.exception;

import com.example.annotation.exception.result.Result;

public class BussinessException extends SystemException {

    private static final long serialVersionUID = 3427150318326046784L;

    public BussinessException(String code, String message) {
        super(code,message);
    }
}
