package com.fan.smsrt.util.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zjg
 * @date 2018/7/17 21:14
 * @Description 统一的返回结果
 */
@Data
@AllArgsConstructor
public class Result<T> {

    //返回枚举代码
    private ResultCodeEnum resultCode;
    //返回信息
    private String message;
    //返回响应数据
    private Object data;

    public Result (ResultCodeEnum resultCodeEnum) {
        this.resultCode = resultCodeEnum;
    }

    public Result (ResultCodeEnum resultCodeEnum , Object data) {
        this.resultCode = resultCodeEnum;
        this.data = data;
    }
}