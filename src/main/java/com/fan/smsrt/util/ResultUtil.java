package com.fan.smsrt.util;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/22 09:57
 * @Description:
 */

public class ResultUtil {

    public static ResultTwo success(String []object) {
        ResultTwo results = new ResultTwo();
        results.setErrno(0);
        results.setData(object);
        return results;
    }

    public static ResultTwo success() {
        return success(null);
    }

}
