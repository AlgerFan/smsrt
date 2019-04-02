package com.fan.smsrt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  AlgerFan
 * @date 2018/7/21 15:26
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    private int goodsId;
    private int storeId;
    private String name;//
    private int alreadySell;
    private float money;//
    private String imagePath;
    private String details;
    private int inventory;//
    private String type;//
    private String specification;//

}