package com.jasper.manager;

import com.jasper.common.anno.EnableGlobalExceptionHandler;
import com.jasper.common.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan(basePackages = "com.jasper.manager.mapper")
//@EnableGlobalExceptionHandler
@Import({GlobalExceptionHandler.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
