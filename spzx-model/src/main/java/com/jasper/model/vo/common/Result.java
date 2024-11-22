package com.jasper.model.vo.common;

import lombok.Data;

@Data
public class Result<T> {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 私有化构造
     */
    private Result() {}


    // 返回数据
    // NOTE <T> 在 Result<T> 前面显式声明了这是一个泛型方法
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> ok(T body) {
        return build(body, 200, "响应成功");
    }
    public static <T> Result<T> ok() {
        return ok(null);
    }
    public static <T> Result<T> fail(String message) {
        return build(null, 500, message);
    }


}
