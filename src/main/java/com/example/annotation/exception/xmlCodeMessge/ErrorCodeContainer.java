package com.example.annotation.exception.xmlCodeMessge;

import com.example.annotation.exception.xmlCodeMessge.errorDto.ErrorCodeDefinitionBean;
import com.example.annotation.exception.xmlCodeMessge.errorDto.ErrorContainerDefinition;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 *  这是一个处理预定义的错误信息内容的类
 */
@Slf4j
public class ErrorCodeContainer {

    /**
     *  启动加载的异常信息容器
     */
    private final static Map<String,Map<String,Map<String,String>>> LANGUAGE_ERROR_MAP =  new HashMap<>();

    /**
     *  默认语言
     */
    private final static String DEFAULT_LANGUAGE =  "CH_ZH";


    static {
        // 加载错误码到容器中
        LANGUAGE_ERROR_MAP.put(DEFAULT_LANGUAGE,new HashMap<>());
    }

    public ErrorCodeContainer() {
    }

    public static String getErrorMessage(String code, String... param) {

        return null;
    }

    /**
     *  初始化错误码内容
     * @param errorBeans
     */
    public static void inite(ArrayList<ErrorCodeDefinitionBean> errorBeans) {
        errorBeans.forEach(ErrorCodeContainer::loadErrorBeanConfig);
    }

    private static void loadErrorBeanConfig(ErrorCodeDefinitionBean errorBean) {
        String language = errorBean.getLanguage();
        LANGUAGE_ERROR_MAP.computeIfAbsent(language, k -> new HashMap<>()); // 如果language为null的话new一个数组

        HashMap<String, String> codeMessage = new HashMap<>();
        List<ErrorContainerDefinition> errorContainerDefinitions = errorBean.getErrorContainerDefinitions();
        errorContainerDefinitions.forEach(each -> {
            codeMessage.put(each.getCode(),each.getMessage());
        });

        String id = errorBean.getId();
        Map<String, Map<String, String>> errorMap = LANGUAGE_ERROR_MAP.get(language);
        if (errorMap.containsKey(id)){
            errorMap.remove(id);
        }
        errorMap.put(id,codeMessage);

        log.info("错误码加载完毕...");
    }
}
