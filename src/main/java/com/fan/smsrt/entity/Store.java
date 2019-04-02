package com.fan.smsrt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjg
 * @date 2018/7/21 15:13
 * @Description 店铺
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store{

    private int storeId;   //店铺Id
    private String userName;   //用户名字
    private String storeName;    //店铺名字
    private String storeIntroduction;  //店铺简介
    private String userIdCard;  //身份证正面照
    private int daySales;   //日销量
    private int monthSales;  //月销量
    private int dayClicks;   //日点击量
    private int totalSales;    //总销量

}
