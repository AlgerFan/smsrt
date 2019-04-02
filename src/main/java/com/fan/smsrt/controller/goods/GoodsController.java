package com.fan.smsrt.controller.goods;

import com.fan.smsrt.service.GoodService;
import com.fan.smsrt.util.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.FileNotFoundException;

/**
 * @author 阿杰
 * @date 2018/7/21 17:36
 * @Description
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodService goodService;

    /**
     * @Description 查找所有商品
     * @Date 2018/7/25 16:48
     * @Param [model]
     **/
    @GetMapping("/")
    public Result selectAllGoods (@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(name = "pageSize", defaultValue = "30") int pageSize) throws FileNotFoundException {
        return goodService.selectAll(pageNum,pageSize);
    }

    /**
     * @Description 根据id查询商品 商品详情
     * @Date 2018/7/26 7:56
     * @Param [goodsId]
     **/
    @GetMapping("/selectGoodsById")
    public Result selectGoodsById (int goodsId) {
        return goodService.selectGoodsById(goodsId);
    }

    /**
     * @Description 搜索商品
     * @Date 2018/7/25 17:29
     * @Param [search, pageNum, pageSize]
     */
    @GetMapping("/searchGoods")
    public Result searchGoods(String search, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize", defaultValue = "30") int pageSize){
        return goodService.searchGoods(search,pageNum,pageSize);
    }

    /**
     * @Description 按分类搜索商品
     * @Date 2018/7/25 17:44
     * @Param [search, pageNum, pageSize]
     **/
    @GetMapping("/searchType")
    public Result typeSearchGoods(String type,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize", defaultValue = "30") int pageSize){
        return goodService.typeSearchGoods(type,pageNum,pageSize);
    }

}
