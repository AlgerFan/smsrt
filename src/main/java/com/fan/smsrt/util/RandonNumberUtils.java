package com.fan.smsrt.util;

import java.util.*;

public class RandonNumberUtils {

    /**
     * @author 阿杰
     * @return java.lang.String
     * @description 利用UUID取得订单号
     */
    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();//生成UUID然后取它的hashCode值
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        //hashCode 以31为权，每一位为字符的ASCII值进行运算,
        // hashcode其实就是散列码，hashcode使用高效率的哈希算法来定位查找对象,尽量减少有同样的hash地址
        return machineId + String.format("%015d", hashCodeV);
    }
}
