package com.example.annotation.exception.xmlCodeMessge.errorDto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorCodeDefinitionBean {

    /**
     *  错误文件码标识
     */
    private String id;

    /**
     *  语言
     */
    private String language;

    /**
     *  错误码标定内容
     */
    private List<ErrorContainerDefinition> errorContainerDefinitions;




}
