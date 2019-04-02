package com.fan.smsrt.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author AlgerFan
 * @date 2018/7/10 13:40
 * @Description
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private int userId;
    private String nickname;      //昵称-
    private String userName;      //用户名-
    private String email;         //邮箱-
    private String password;      //密码-
    private String phone;         //手机-
    private String acceptAddress; //头像地址
    private String city;          //地址-
    private int roleId;           //角色-
    private int status;           //是否有店铺
    private int storeId;          //店铺Id

    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }
    public User(String userName,String password,String phone){
        this.userName=userName;
        this.password=password;
        this.phone=phone;
    }

}
