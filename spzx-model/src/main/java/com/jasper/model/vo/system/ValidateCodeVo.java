package com.jasper.model.vo.system;

import lombok.Data;

@Data
public class ValidateCodeVo {
    private String codeKey;   // 验证码的key
    private String codeValue; // 图片对应的字符串数据
}
