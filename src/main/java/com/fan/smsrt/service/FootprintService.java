package com.fan.smsrt.service;

import com.fan.smsrt.util.result.Result;

public interface FootprintService {

    //添加足迹
    Result createFootprint(int userId, int goodsId);

    //查看所有足迹
    Result findAllFootprint(int userId);

    //删除足迹
    Result deleteFootprint(int footprintId);
}
