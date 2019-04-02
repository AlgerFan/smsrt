package com.fan.smsrt.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 阿杰
 * @Date: 2018/5/5 15:05
 * @Description:
 */
public class IdsUtil {
    public static List<String> listIds(String ids){
        List<String> stringList = new ArrayList<>();
        if(ids.contains(",")){
            String[] str = ids.split(",");
            if (str.length != 0) {
                stringList.addAll(Arrays.asList(str));
            }
        }
        if(!ids.contains(",")){
            stringList.add(ids);
        }
        return stringList;
    }
    public static int[] intIds(String ids) {
        int[] ints = new int[0];
        if (ids.contains(",")) {
            String[] str = ids.split(",");
            if (str.length != 0) {
                ints = new int[str.length];
                for (int i = 0; i < str.length; i++) {
                    ints[i] = Integer.parseInt(str[i]);
                }
            }
        }
        if(!ids.contains(",")){
            ints[0] = Integer.parseInt(ids);
        }
        return ints;
    }

}
