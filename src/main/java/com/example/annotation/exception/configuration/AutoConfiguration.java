package com.example.annotation.exception.configuration;

import com.example.annotation.exception.ExceptionHandleAdvice;
import com.example.annotation.exception.xmlCodeMessge.ExceptionFactoryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({ExceptionHandleAdvice.class,ExceptionFactoryPostProcessor.class})
@ImportResource("classpath:config/*_errorCode_*.xml")
public class AutoConfiguration {

}
