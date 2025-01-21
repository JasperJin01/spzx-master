package com.jasper.common.exception;


import com.jasper.model.vo.common.Result;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 这种处理方法并不会在业务层被感知到，而interceptor是业务逻辑本身的一部分（例如用户认证，使用登录拦截器）
@RestControllerAdvice // TODO 专用于 RESTful 风格 API 的全局增强注解（意思是所有的RestController都需要注入这个方法）
public class GlobalExceptionHandler {

    // NOTE 通知：前置、后置、环绕、返回、异常
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        // 打印异常信息
        System.out.println("程序出现通用异常，打印异常信息: ");
        e.printStackTrace();

        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentExceptionHandler(IllegalArgumentException e) {
        System.out.println("程序出现非法参数异常，打印异常信息：");
        e.printStackTrace();

        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        System.out.println("程序出现请求方法不支持异常，打印异常信息：");
        e.printStackTrace();
        return Result.fail("请求方法不支持");
    }




}
