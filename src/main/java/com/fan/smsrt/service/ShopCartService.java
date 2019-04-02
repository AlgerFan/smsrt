package com.fan.smsrt.service;

import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.entity.ShopCart;

import java.util.List;

public interface ShopCartService {

    /**
     * [添加购物车]
     * @param goodsId
     * @param userId
     */
    Result addShopCart(int goodsId, int userId, int count);

    /**
     * [从购物车移除]
     * @param shopCardId
     * @param useId
     */
    Result deleteShopCart(int shopCardId, int useId);

    /**
     * [批量从购物车移除]
     * @param shopCardIds
     * @param userId
     */
    Result deleteShopCart(String shopCardIds, int userId);

    /**
     * 查询我的购物车
     * @param userId
     */
    Result selectShopCards(int userId);

}
