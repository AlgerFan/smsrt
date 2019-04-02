package com.fan.smsrt.service;

import com.fan.smsrt.entity.Goods;
import com.fan.smsrt.util.result.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 阿杰
 * @date 2018/7/21 17:45
 * @Description
 */
public interface GoodService {

    //查找所有商品
    Result selectAll(int pageNum, int pageSize);

    //添加商品
    Result insert(int storeId, Goods goods, MultipartFile[] myFileNames, HttpServletRequest request, HttpSession session);

    //删除商品
    Result delete(int goodsId,int storeId);

    //根据id查询商品
    Result selectGoodsById(int goodsId);

    //修改商品信息
    Result update(Goods goods, int goodsId, int storeId);

    //搜索商品
    Result searchGoods(String search, int pageNum, int pageSize);

    //按分类搜索商品
    Result typeSearchGoods(String search, int pageNum, int pageSize);

    //查询店铺商品
    Result selectStoreGoods(int pageNum, int pageSize, int storeId);

    //查询所有商品
    PageInfo selectGoods(String name, int pageNum, int pageSize);

    //富文本添加图片
    String[] uploadImg(MultipartFile myFileName, HttpSession session, HttpServletRequest request);

    //后台添加商品
    Result adminInsert(Goods goods, MultipartFile myFileName, HttpServletRequest request, HttpSession session);

    //后台删除商品
    Result adminDelete(int goodsId);

    //后台修改商品信息
    Result adminUpdate(Goods goods, int goodsId);

}
