package com.fan.smsrt.controller.goods;

import com.fan.smsrt.entity.Goods;
import com.fan.smsrt.entity.Store;
import com.fan.smsrt.service.GoodService;
import com.fan.smsrt.service.OrderService;
import com.fan.smsrt.service.StoreService;
import com.fan.smsrt.util.result.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 阿杰
 * @date 2018-07-25 16:11
 * @Description
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private GoodService goodService;
    @Resource
    private StoreService storeService;
    @Resource
    private OrderService orderService;


    /**
     * @Description 创建店铺
     * @Date 2018/7/26 22:00
     * @Param [store, session]
     **/
    @PostMapping("/createStore")
    public Result createStore (Store store, int userId) {
        return storeService.createStore(store,userId);
    }

    /**
     * @Description 查看店铺信息
     * @Date 2018/7/28 11:04
     * @Param [userId]
     **/
    @GetMapping("/selectStore")
    public Result selectStore(int storeId){
        return storeService.selectStore(storeId);
    }

    /**
     * @Description 查询店铺商品
     * @Date 2018/7/26 22:06
     * @Param [pageNum, pageSize, request]
     **/
    @GetMapping("/selectGoods")
    public Result selectStoreGoods (int storeId,
                                    @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return goodService.selectStoreGoods(pageNum,pageSize,storeId);
    }

    /**
     * @Description 添加商品
     * @Date 2018/7/25 16:48
     * @Param [goods]
     **/
    @PostMapping("/insertGood")
    public Result insertGood (int storeId, Goods goods, @RequestParam("file") MultipartFile[] myFileNames,
                              HttpSession session, HttpServletRequest request) {
        return goodService.insert(storeId,goods,myFileNames,request,session);
    }

    /**
     * @Description 删除商品
     * @Date 2018/7/25 16:48
     * @Param [goodsId]
     **/
    @DeleteMapping("/delete")
    public Result delete(int goodsId,int storeId){
        return goodService.delete(goodsId,storeId);
    }

    /**
     * @Description 修改商品信息
     * @Date 2018/7/25 16:49
     * @Param [goods,goodsId]
     **/
    @PostMapping("/updateGoods")
    public Result updateGood (Goods goods,int goodsId,int storeId) {
        return goodService.update(goods,goodsId,storeId);
    }

    /**
     * @Description 查找店铺订单
     * @Date 2018/7/27 20:05
     * @Param [pageNum, pageSize, storeId]
     **/
    @GetMapping("/selectStoreOrder")
    public Result selectStoreOrder (int storeId,
                                    @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.selectStoreOrder(pageNum,pageSize,storeId);
    }
}
