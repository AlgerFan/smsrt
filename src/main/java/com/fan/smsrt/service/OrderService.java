package com.fan.smsrt.service;

import com.alipay.api.AlipayApiException;
import com.fan.smsrt.util.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OrderService {

    void ali(String total_money, HttpServletResponse response) throws AlipayApiException, IOException;

    /**
     * 生成订单
     */
    Result addOrder(String total_money, String goodsIds, String shoppingCounts, int userId);

    /**
     * 收货，退货
     */
    Result updateOrderStatus(int shopCardId, int userId);

    /**
     * 查询个人订单，订单状态
     */
    Result selectOrderList(int userId, int pageNum, int pageSize, int status);

    /**
     * 查询订单详情
     */
    Result selectOrder(int id, int pageNum, int userId, int status);

    /**
     * 查找店铺订单
     */
    Result selectStoreOrder(int pageNum, int pageSize, int storeId);

}
