package com.fan.smsrt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author AlgerFan
 * @date 2018/7/21 17:50
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopCart {

    private int shopCartId;      //购物车Id
    private int goodsId;         //商品Id
    private int userId;          //用户Id
    private int storeId;         //店铺Id
    private String storeName;    //店铺名字
    private String goodsName;    //商品名字
    private String imagePath;    //商品图片
    private float money;         //商品价格
    private String specification;//商品规格
    private int goodsCount;      //商品数量
    private Date buildDate;      //创建时间

    public ShopCart(int goodsId, int userId, int storeId, String storeName, String goodsName,
                    String imagePath, float money, String specification, int goodsCount, Date buildDate) {
        this.goodsId = goodsId;
        this.userId = userId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.goodsName = goodsName;
        this.imagePath = imagePath;
        this.money = money;
        this.specification = specification;
        this.goodsCount = goodsCount;
        this.buildDate = buildDate;
    }
}
