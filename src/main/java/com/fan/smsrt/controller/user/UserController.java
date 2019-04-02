package com.fan.smsrt.controller.user;

import com.fan.smsrt.entity.User;
import com.fan.smsrt.service.UserService;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author AlgerFan
 * @date 2018/7/10 13:56
 * @Description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 通过id查找用户
     * @param userId
     */
    @GetMapping("/selectUserById")
    public Result selectUserById(int userId){
        return userService.selectUser(userId);
    }

    /**
     * 修改用户个人信息
     * @param user
     */
    @PostMapping("/updateUser")
    public Result updateUser(int userId,User user){
        return userService.updateUser(userId,user);
    }

    /**
     * 修改用户密码
     * @param userId
     * @param password
     * @param newPassword
     */
    @PostMapping("/retrievePassword")
    public Result retrievePassword(int userId,String password,String newPassword){
         return userService.retrievePassword(userId,password,newPassword);
    }

}
