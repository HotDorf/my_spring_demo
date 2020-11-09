package com.example.annotation.exception;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *  这是一个处理异常的类
 */
public class ExceptionHandler {

    /**
     *  封装并抛出异常,将异常分类处理
     */
    public static void packThrow(Logger logger, Throwable throwable){
        Throwable t = getRealThrowable(throwable);
        //switch (t.getClass().getSimpleName())
        if (t instanceof SystemException){
            throw (SystemException)t;
        } else if (t instanceof BussinessException){
            throw (BussinessException)t;
        } else {
            logger.error(getExceptionStack(t));
            throw new SystemException("Exception0000");
        }
    }

    /**
     * 获取Exception Cause的错误信息
     */
    private static Throwable getRealThrowable(Throwable throwable){
        Throwable cause = throwable.getCause();
        if (cause != null){
            throwable = getRealThrowable(cause); //获取最里层的错误信息
        }
        return throwable;
    }


    /**
     *  获取异常堆栈信息
     */
    public static String getExceptionStack(Throwable e){
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;

        try {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
        } finally {
            if (stringWriter != null){
                try {
                    stringWriter.close();
                }catch (IOException io){
                    io.printStackTrace();
                }
                if (printWriter!=null){
                    printWriter.close();
                }
            }
        }
        return stringWriter.toString();
    }

}
