package com.fan.smsrt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 阿杰
 * @create 2018-07-28 9:57
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Footprint {

    private int footprintId;    //足迹id
    private int userId;     //用户id
    private int goodsId;     //商品id
    private String name;    //商品名
    private float money;    //商品价格
    private String imagePath;   //商品图片
    private String storeName;    //店铺名字
    private Date footprintTime;  //足迹时间

    public Footprint(int userId, int goodsId, String name, float money, String imagePath, String storeName, Date footprintTime) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.name = name;
        this.money = money;
        this.imagePath = imagePath;
        this.storeName = storeName;
        this.footprintTime = footprintTime;
    }
}
