package com.fan.smsrt.controller;

import com.fan.smsrt.entity.User;
import com.fan.smsrt.service.FootprintService;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author AlgerFan
 * @create 2018-07-28 10:03
 * @Description:
 */
@RestController
@RequestMapping("/footprint")
public class FootprintController {

    @Resource
    private FootprintService footprintService;

    /**
     * @Description 添加足迹
     * @Date 2018/7/28 11:09
     * @Param [goodsId, request]
     **/
    @PostMapping("/createFootprint")
    public Result createFootprint (int goodsId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null) {
            return new Result(ResultCodeEnum.FAIL);
        }
        return footprintService.createFootprint(user.getUserId(),goodsId);
    }
    /**
     * @Description 查看所有足迹
     * @Date 2018/7/28 11:09
     * @Param [request]
     **/
    @GetMapping("/findAllFootprint")
    public Result findAllFootprint(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null) {
            return new Result(ResultCodeEnum.FAIL);
        }
        return footprintService.findAllFootprint(user.getUserId());
    }
    /**
     * @Description 删除足迹
     * @Date 2018/7/28 11:10
     * @Param [footprintId]
     **/
    @RequestMapping("/deleteFootprint")
    public Result deleteFootprint(int footprintId){
        if(footprintId==0){
            return new Result(ResultCodeEnum.FAIL);
        }
        return footprintService.deleteFootprint(footprintId);
    }
}
