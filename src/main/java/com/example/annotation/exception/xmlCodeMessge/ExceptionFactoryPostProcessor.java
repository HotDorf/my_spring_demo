package com.example.annotation.exception.xmlCodeMessge;

import com.example.annotation.exception.xmlCodeMessge.errorDto.ErrorCodeDefinitionBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

import java.util.ArrayList;
import java.util.Map;

/**
 *  这个类是用于加载文件
 */
@Slf4j
public class ExceptionFactoryPostProcessor implements BeanFactoryPostProcessor,Ordered{

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      log.info("开始加载错误码...");
        Map<String, ErrorCodeDefinitionBean> beansOfType = beanFactory.getBeansOfType(ErrorCodeDefinitionBean.class);
        ArrayList<ErrorCodeDefinitionBean> errorBeans = new ArrayList<>();
        beansOfType.forEach((key, value) -> {
            errorBeans.add(value);
        });
        ErrorCodeContainer.inite(errorBeans);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
