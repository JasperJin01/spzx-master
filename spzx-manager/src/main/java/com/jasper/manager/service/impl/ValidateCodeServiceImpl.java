package com.jasper.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.jasper.manager.service.ValidateCodeService;
import com.jasper.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ValidateCodeVo getCaptcha() {
        // 验证码图片String、key、值

        // 使用hutool工具包中的工具类生成图片验证码

        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 10);
        String code = circleCaptcha.getCode(); // 验证码的值
        String codeValue = "data:image/png;base64," + circleCaptcha.getImageBase64();

        // 生成验证码key并存入缓存
        String key = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:captcha:" + key, code, 10, TimeUnit.MINUTES);

        ValidateCodeVo codeVo = new ValidateCodeVo();
        codeVo.setCodeKey(key);
        codeVo.setCodeValue(codeValue);

        return codeVo;
    }
}
