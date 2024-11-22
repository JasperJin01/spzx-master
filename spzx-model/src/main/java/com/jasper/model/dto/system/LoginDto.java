package com.jasper.model.dto.system;

import lombok.Data;

@Data
public class LoginDto {
    private String userName;
    private String password;
    private String captcha;
    private String codeKey;
}
