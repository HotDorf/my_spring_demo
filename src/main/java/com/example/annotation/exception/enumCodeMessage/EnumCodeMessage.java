package com.example.annotation.exception.enumCodeMessage;

import lombok.Data;
import lombok.Getter;

@Getter
public enum EnumCodeMessage {

    ONE_ERROR("0001","[%s]出问题了");

    private String code;

    private String message;

    EnumCodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
