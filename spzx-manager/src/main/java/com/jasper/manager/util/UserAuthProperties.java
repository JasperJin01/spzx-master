package com.jasper.manager.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spzx.auth")
@Component
public class UserAuthProperties {
    private List<String> excludeUrls;

}
