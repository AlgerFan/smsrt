package com.fan.smsrt.service;

import com.fan.smsrt.entity.User;
import com.fan.smsrt.util.result.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author AlgerFan
 * @date 2018/7/10 13:52
 * @Description
 */
public interface UserService {

    /**
     * 登录
     * @param phone
     * @param password
     */
    Result login(String phone,String password);

    /**
     * 检查用户名是否存在
     * @param userName
     */
    Result checkUserName(String userName);

    /**
     * 检查手机号是否存在
     * @param phone
     */
    Result checkPhone(String phone);

    /**
     * 注册
     * @param username
     * @param password
     * @param phone
     */
    Result resister(String username, String password, String phone);

    /**
     * 通过id查询用户
     * @param id
     */
    Result selectUser(int id);

    /**
     * 更新用户资料 nickname 昵称  email 邮箱  phone 手机号  acceptAddress 头像地址 city 地址
     * @param user
     */
    Result updateUser(int userId,User user);

    /**
     * 找回密码
     * @param password
     * @param newPassword
     */
    Result retrievePassword(int userId,String password,String newPassword);


    User adminCheckUserName(String userName);

    User adminCheckPhone(String phone);
    /**
     * 后台添加用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 分页查询用户
     * @param user_name
     * @param pageNum
     * @param pageSize
     */
    PageInfo selectUserPage(String user_name, int pageNum, int pageSize);

    /**
     * 根据id删除用户
     * @param userId
     */
    Result deleteById(int userId);

    /**
     * 后台修改用户
     * @param userId
     * @param user
     */
    void adminUpdateUser(int userId, User user);

    User findById(int userId);
}
