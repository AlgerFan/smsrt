package com.fan.smsrt.util.result;

import lombok.Getter;

/**
 * @author zjg
 * @date 2018/7/17 21:15
 * @Description 统一返回代码枚举
 */
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),//通过
    FAIL(400,"失败"), //失败
    NOTFOUND(404,"请求资源不存在"), //没有找到资源
    UNAUTHORIZEd(401,"未认证"),//未认证，签名错误
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"), //服务器错误
    ;

    @Getter private int code;

    @Getter private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
