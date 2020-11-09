package com.example.annotation.dynamicProxy.springAOP.utils;

import com.alibaba.fastjson.JSON;
import com.example.annotation.poi.ExcelUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Component
@Aspect //切面
@EnableAspectJAutoProxy(proxyTargetClass = true) //强制使用cglib动态代理实现，默认false使用jdk代理
public class Logger {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    //切入点
    @Pointcut("execution(* com.example.annotation.poi.service.*.*(..))")
    public void point(){};
    /**
     * 前置通知
     */
    public  void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
    }

    /**
     * 后置通知
     */
    public  void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了。。。");
    }
    /**
     * 异常通知
     */
    public  void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
    }

    /**
     * 最终通知
     */
    public  void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
    @Around("point()")
    public Object aroundPringLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            String className = pjp.getTarget().getClass().getSimpleName();
            String methodName = pjp.getSignature().getName();
            Object[] args = pjp.getArgs();//得到方法执行所需的参数
            String argsName = JSON.toJSONString(args);
            StringBuilder startSB = new StringBuilder();
            startSB.append(className).append(".").append(methodName).append("(").append(argsName).append(")").append("执行了....!");
            logger.info(startSB.toString());
            //System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");

            rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）

            StringBuilder endSB = new StringBuilder();
            endSB.append(className).append(".").append(methodName).append("(").append(argsName).append(")").append("结束了....!");
            logger.info(endSB.toString());
            //System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");
            //int i = 1/0;

            return rtValue;
        }catch (Throwable t){
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");
            throw new RuntimeException(t);
        }finally {
            logger.info("finally=====");
            /*System.out.println(pjp.getTarget().getClass().getSimpleName()+pjp.toShortString()+"Logger类中的aroundPringLog方法开始记录日志了。。。最终");
            System.out.println("pjp的API：pjp.toShortString() === "+pjp.toShortString());
            System.out.println("pjp的API：pjp.toLongString() === "+pjp.toLongString());
            System.out.println("pjp的API：pjp.toString() === "+pjp.toString());
            System.out.println("pjp的API：pjp.getKind() === "+pjp.getKind()); //method-execution
            System.out.println("pjp的API：pjp.getSignature().getName() === "+pjp.getSignature().getName()); //saveAccount
            System.out.println("pjp的API：pjp.getSignature().getDeclaringType() === "+pjp.getSignature().getDeclaringType());
            System.out.println("pjp的API：pjp.getSignature().getDeclaringTypeName() === "+pjp.getSignature().getDeclaringTypeName());
            System.out.println("pjp的API：pjp.getSignature().getModifiers() === "+pjp.getSignature().getModifiers());
            System.out.println("pjp的API：pjp.getSourceLocation() === "+pjp.getSourceLocation());
            System.out.println("pjp的API：pjp.getThis() === "+pjp.getThis().getClass().getSimpleName()); //AccountServiceImpl$$EnhancerBySpringCGLIB$$8e5b524b
            System.out.println("pjp的API：pjp.getTarget() === "+pjp.getTarget().getClass().getSimpleName()); //AccountServiceImpl*/
        }
    }
}
