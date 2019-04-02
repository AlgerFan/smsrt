package com.fan.smsrt.controller;

import com.alipay.api.AlipayApiException;
import com.fan.smsrt.entity.User;
import com.fan.smsrt.service.OrderService;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/ali")
    public ModelAndView ali(String total_money, HttpServletResponse response) throws IOException, AlipayApiException {
        orderService.ali(total_money,response);
        return new ModelAndView("aliPay");
    }

    /**
     * 创建订单
     */
    @RequestMapping("/buildOrder")
    public Result buildOrder(String total_money, String goodsIds, String shoppingCounts, int userId) {
        return orderService.addOrder(total_money,goodsIds,shoppingCounts,userId);
    }

    /**
     * 分类查看订单，若查全部，status为空字符串
     * @param status   订单状态
     * @param userId   userId
     * @param pageNum  当前页数
     * @param pageSize 每次查询数量
     */
    @GetMapping("/seeOrder")
    public Result seeOrders(int status, int userId,
                            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "30") int pageSize) {
        return orderService.selectOrderList(userId, pageNum, pageSize, status);
    }

    /**
     * 收货
     * @param orderId
     * @param userId
     */
    @PostMapping("/update")
    public Result updateOrderStatus(int orderId, int userId) {
        return orderService.updateOrderStatus(orderId, userId);
    }

    /**
     * [查看订单详情]
     *
     * @param orderId
     */
    @GetMapping("/seeDetails")
    public Result seeDetails(int userId, int orderId) {
        /*User userSession = (User) session.getAttribute("user");
        if(userSession == null || userId != userSession.getUserId()) return new Result(ResultCodeEnum.FAIL);*/
        return orderService.selectOrder(userId, 1, userId,orderId);
    }

}
