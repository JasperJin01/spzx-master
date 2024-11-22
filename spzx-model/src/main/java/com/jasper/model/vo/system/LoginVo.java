package com.jasper.model.vo.system;

import lombok.Data;

@Data
public class LoginVo {
    private String token;
    // FIXME 这里应该写成_还是驼峰？
    private String refresh_token;
}
