package com.jasper.manager.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户认证配置，配置哪些请求不需要认证
 * 
 * 读取yml/properties文件中的spzx.auth.excludeUrls配置
 * Spring 提供了 @ConfigurationProperties 注解，用于将配置文件中的属性值绑定到对象的属性上
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth") // 读取yml/properties文件中的spzx.auth.excludeUrls配置
@Component
public class UserAuthProperties {
    private List<String> excludeUrls;

}
