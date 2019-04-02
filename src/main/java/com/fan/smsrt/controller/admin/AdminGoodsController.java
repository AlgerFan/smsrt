package com.fan.smsrt.controller.admin;

import com.fan.smsrt.entity.Goods;
import com.fan.smsrt.service.GoodService;
import com.fan.smsrt.util.ResultTwo;
import com.fan.smsrt.util.ResultUtil;
import com.fan.smsrt.util.result.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author AlgerFan
 * @date Created in 2018/12/12 17
 * @Description
 */
@RestController
@RequestMapping("/admin")
public class AdminGoodsController {
    @Resource
    private GoodService goodService;

    /**
     * @Description 查询所有商品  根据name搜索商品
     * @Date 2018/7/26 22:06
     * @Param [pageNum, pageSize, request]
     **/
    @GetMapping("/selectGoods")
    public ModelAndView selectStoreGoods (Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        String name="";
        PageInfo pageInfo = goodService.selectGoods(name,pageNum, pageSize);
        model.addAttribute("TotalPages", pageInfo.getPages());//查询的页数
        model.addAttribute("Number", pageInfo.getPageNum());//查询的当前第几页
        model.addAttribute("goods", pageInfo.getList());//查询的当前页的集合
        return new ModelAndView("goods/goodsList");
    }
    /**
     * 跳转至添加商品
     */
    @RequestMapping("/toAddGoods")
    public ModelAndView toAddGoods(){
        return new ModelAndView("goods/addGoods");
    }

    /**
     * @Description 富文本添加图片
     * @Date 2018/7/26 16:33
     * @Param [myFileName, session, request]
     **/
    @RequestMapping("/upload")
    public ResultTwo uploadImg(MultipartFile file, HttpSession session, HttpServletRequest request) {
        String[] str = goodService.uploadImg(file, session, request);
        return ResultUtil.success(str);
    }

    /**
     * @Description 添加商品
     * @Date 2018/7/25 16:48
     * @Param [goods]
     **/
    @PostMapping("/insertGood")
    public ModelAndView insertGood (Goods goods, @RequestParam("file") MultipartFile myFileName, HttpSession session,
                              HttpServletRequest request) {
        Result result = goodService.adminInsert(goods, myFileName, request, session);
        if(result.getResultCode().getCode()==400){
            return new ModelAndView("goods/addGoods");
        }
        return new ModelAndView("redirect:/admin/selectGoods");
    }

    /**
     * @Description 删除商品
     * @Date 2018/7/25 16:48
     * @Param [goodsId]
     **/
    @DeleteMapping("/delete")
    public Result delete(int goodsId){
        return goodService.adminDelete(goodsId);
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
     * @Description 修改商品信息 只能修改 name,money,inventory,type,specification五个字段
     * @Date 2018/7/25 16:49
     * @Param [goods,goodsId]
     **/
    @PostMapping("/updateGoods")
    public Result updateGood (Goods goods,int goodsId) {
        return goodService.adminUpdate(goods,goodsId);
    }

}
