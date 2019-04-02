package com.fan.smsrt.controller;

import com.fan.smsrt.service.UserService;
import com.fan.smsrt.util.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author AlgerFan
 * @date 2018/7/8 20:14
 * @Description
 */
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param phone
     * @param password
     */
    @PostMapping("/login")
    public Result login(String phone, String password) {
        return userService.login(phone, password);
    }

    /**
     * 检查用户名是否存在
     * @param userName
     */
    @GetMapping("/checkUserName")
    public Result checkUserName(String userName){
        return userService.checkUserName(userName);
    }

    /**
     * 检查手机号是否存在
     * @param phone
     */
    @GetMapping("/checkPhone")
    public Result checkPhone(String phone){
        return userService.checkPhone(phone);
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @param phone
     */
    @PostMapping("/resister")
    public Result resister(String username, String password,String phone) {
        return userService.resister(username, password, phone);
    }

}
