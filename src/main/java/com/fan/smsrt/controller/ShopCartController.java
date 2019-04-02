package com.fan.smsrt.controller;

import com.fan.smsrt.service.ShopCartService;
import com.fan.smsrt.util.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/shopCart")
public class ShopCartController {
    @Resource
    private ShopCartService shopCartService;

    /**
     * 添加购物车，或者改变数量
     * @param goodsId
     * @param count
     * @param userId
     */
    @PostMapping("/add")
    public Result addShopCart(int goodsId, int count, int userId) {
        return shopCartService.addShopCart(goodsId, userId, count);
    }

    /**
     * 从购物车中删除
     * @param shopCartId 购物车Id
     * @param userId
     */
    @DeleteMapping("/delete")
    public Result deleteShopCart(@RequestParam("userId") int userId,@RequestParam("shopCartId") int shopCartId) {
         return shopCartService.deleteShopCart(shopCartId, userId);
    }

    /**
     * 购物车批量删除
     * @param shopCarts
     * @param userId
     */
    @DeleteMapping("/deletes")
    public Result deletesShopCart(@RequestParam("userId") int userId, @RequestParam("shopCartId") String shopCarts) {
        return shopCartService.deleteShopCart(shopCarts, userId);
    }

    /**
     * 查看我的购物车
     * @param userId
     */
    @GetMapping("/find")
    public Result myShopCart(int userId) {
        return shopCartService.selectShopCards(userId);
    }

}
