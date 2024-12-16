package com.jasper.manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI createRestApi() {
        return new OpenAPI().info(
                new Info()
                .title("尚品甑选API接口文档")
                .version("1.0")
                .description("尚品甑选API接口文档")
                .contact(new Contact().name("Jasper"))
        ); // 设定作者

    }

}
