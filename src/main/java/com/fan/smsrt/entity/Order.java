package com.fan.smsrt.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AlgerFan
 * @date 2018/7/21 15:17
 * @Description
 */
@Data
@NoArgsConstructor
public class Order implements Serializable{

    private int orderId;       //订单Id
    private int userId;        //买家id
    private String userName;   //用户名
    private String orderStatus;//发货中，待收货，交易完成
    private Date orderDate;    //交易时间，年月日
    private String phone;      //手机号
    private String address;    //地址

    private int count;         //购买数量
    private String goodsName;  //商品名字
    private String imagePath;  //商品图片
    private float money;       //商品价格
    private String specification;//商品规格

    public Order(int userId, String userName, String orderStatus, Date orderDate, String phone, String address, int count, String goodsName, String imagePath, float money, String specification) {
        this.userId = userId;
        this.userName = userName;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.phone = phone;
        this.address = address;
        this.count = count;
        this.goodsName = goodsName;
        this.imagePath = imagePath;
        this.money = money;
        this.specification = specification;
    }
}
