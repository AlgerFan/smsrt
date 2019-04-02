package com.fan.smsrt.util;

/**
 * @Author 阿杰
 * @Description 检验图片格式
 * @Date 2018/7/19 16:00
 **/
public class CheckImage {

    public static boolean verifyImage(String fileName){
        String reg="(?i).+?\\.(jpg|gif|bmp|png|jpeg)";
        return fileName.matches(reg);
    }

    public static boolean verifyImages(String[] fileNames){
        String reg="(?i).+?\\.(jpg|gif|bmp|png|jpeg)";
        for(int i = 0;i<fileNames.length;i++){
            if(!fileNames[i].matches(reg)){
                return false;
            }
        }
        return true;
    }
}
