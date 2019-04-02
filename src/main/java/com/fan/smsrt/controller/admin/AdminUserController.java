package com.fan.smsrt.controller.admin;

import com.fan.smsrt.entity.User;
import com.fan.smsrt.service.UserService;
import com.fan.smsrt.util.result.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author AlgerFan
 * @date Created in 2018/11/29 08
 * @Description
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Resource
    UserService userService;
    /*
        打开首页
     */
    @GetMapping("/toIndex")
    public ModelAndView toIndex() {
        return new ModelAndView("/index");
    }
        /*
           欢迎界面
       */
    @GetMapping("/HelloPage")
    public ModelAndView toHello() {
        return new ModelAndView("/HelloPage");
    }
    /**
     * 跳转至后台添加用户
     */

    @GetMapping("/toInsertUser")
    public ModelAndView toInsertUser() {
        return new ModelAndView("admin/addUser");
    }
    /**
     * 后台添加用户  (按钮提交地址)
     * @param user
     */
    @PostMapping("/insertUser")
    public ModelAndView insertUser(User user,Model model) {
        System.out.println(user);
        if(user.getUserName().equals("") || user.getUserName()==null ||
                user.getPassword().equals("") || user.getPassword()==null ||
                user.getPhone().equals("") || user.getPhone() == null){
            model.addAttribute("msg","用户名、密码、手机号不能为空！");
            return new ModelAndView("admin/addUser");
        }
        User user1 = userService.adminCheckUserName(user.getUserName());
        if(user1!=null){
            model.addAttribute("msg","用户名已存在！");
            return new ModelAndView("admin/addUser");
        }
        User user2 = userService.adminCheckPhone(user.getPhone());
        if(user2!=null){
            model.addAttribute("msg","手机号已存在！");
            return new ModelAndView("admin/addUser");
        }
        userService.insertUser(user);
        return new ModelAndView("redirect:/admin/selectUser");
    }

    /**
     * 通过id删除用户
     * @param userId
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("userId") int userId){
        return userService.deleteById(userId);
    }

    /**
     * 跳转至修改用户信息
     */
    @GetMapping("/toUpdateUser")
    public ModelAndView toUpdateUser(Model model,int userId) {
        User user = userService.findById(userId);
        model.addAttribute("user",user);
        return new ModelAndView("admin/updateUser");
    }

    /**
     * 修改用户信息   （修改按钮提交）
     * @param user
     */
    @PostMapping("/updateUser")
    public ModelAndView updateUser(int userId,User user,Model model) {
        if(user.getUserName().equals("") || user.getUserName()==null ||
                user.getPassword().equals("") || user.getPassword()==null ||
                user.getPhone().equals("") || user.getPhone() == null){
            model.addAttribute("msg","用户名、密码、手机号不能为空！");
            return new ModelAndView("admin/addUser");
        }
        User user1 = userService.adminCheckUserName(user.getUserName());
        if(!user.getUserName().equals(user.getUserName()) && user1!=null){
            model.addAttribute("msg","用户名已存在！");
            return new ModelAndView("admin/addUser");
        }
        User user2 = userService.adminCheckPhone(user.getPhone());
        if(!user.getPhone().equals(user.getPhone()) && user2!=null){
            model.addAttribute("msg","手机号已存在！");
            return new ModelAndView("admin/addUser");
        }
        userService.adminUpdateUser(userId,user);
        return new ModelAndView("redirect:/admin/selectUser");
    }

    /**
     * 分页查找用户
     * @param pageNum
     * @param pageSize
     */
    @GetMapping("/selectUser")
    public ModelAndView selectUserPage(Model model,
                                       @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        String user_name="";
        PageInfo page = userService.selectUserPage(user_name,pageNum,pageSize);
        model.addAttribute("TotalPages", page.getPages());//查询的页数
        model.addAttribute("Number", page.getPageNum());//查询的当前第几页
        model.addAttribute("users", page.getList());//查询的当前页的集合
        return new ModelAndView("admin/userList");
    }
}
