package com.jasper.common.exception;


import com.jasper.model.vo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // TODO 专用于 RESTful 风格 API 的全局增强注解（意思是所有的RestController都需要注入这个方法）
public class GlobalExceptionHandler {

    // NOTE 通知：前置、后置、环绕、返回、异常
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        // 打印异常信息
        System.out.println("程序出现异常，打印异常信息: ");
        e.printStackTrace();

        return Result.fail(e.getMessage());
    }




}
