package com.fan.smsrt.service.serviceimpl;

import com.fan.smsrt.util.IdsUtil;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import com.fan.smsrt.dao.GoodDao;
import com.fan.smsrt.dao.ShopCartDao;
import com.fan.smsrt.dao.StoreDao;
import com.fan.smsrt.entity.Goods;
import com.fan.smsrt.entity.ShopCart;
import com.fan.smsrt.entity.Store;
import com.fan.smsrt.service.ShopCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ShopCartServiceImpl implements ShopCartService {
    @Resource
    private ShopCartDao shopCartDao;
    @Resource
    private GoodDao goodDao;
    @Resource
    private StoreDao storeDao;

    @Override
    public Result addShopCart(int goodsId, int userId, int count) {
        Result result;
        Goods goods = goodDao.selectById(goodsId);
        if (goods == null) return new Result(ResultCodeEnum.FAIL);
        Store store = storeDao.findStore(goods.getStoreId());
        ShopCart shopCart = shopCartDao.selectShopCard(goodsId, userId);
        if (shopCart == null) {
            System.out.println("userId:"+userId);
            System.out.println("goods："+goods);
            System.out.println(store);
            shopCart = new ShopCart(goods.getGoodsId(), userId, goods.getStoreId(), store.getStoreName(), goods.getName(), goods.getImagePath(), goods.getMoney(), goods.getSpecification(), count, new Date());
            shopCartDao.insertShopCard(shopCart);
        } else {
            if (count > goods.getInventory()) {
                result = new Result(ResultCodeEnum.FAIL);
                result.setMessage("库存不足");
                return result;
            }
            shopCart.setGoodsCount(shopCart.getGoodsCount() + count);
            shopCartDao.updateShopCartCount(shopCart);
        }
        result = new Result(ResultCodeEnum.SUCCESS);
        result.setMessage("添加成功");
        return result;
    }

    @Override
    public Result deleteShopCart(int shopCartId, int useId) {
        Result result;
        ShopCart shopCart = shopCartDao.findShopCartById(shopCartId);
        if (shopCart != null && shopCart.getUserId() == useId) {
            shopCartDao.deleteShopCartById(shopCartId);
            result = new Result(ResultCodeEnum.SUCCESS);
            result.setMessage("删除成功！");
            return result;
        }
        result = new Result(ResultCodeEnum.FAIL);
        result.setMessage("删除失败！");
        return result;
    }

    @Override
    public Result deleteShopCart(String shopCartIds, int userId) {
        Result result;
        List<String> Ids = IdsUtil.listIds(shopCartIds);
        List<ShopCart> shopCarts = shopCartDao.findShopCartByIds(Ids);
        for (ShopCart shopCart : shopCarts) {
            if (shopCart.getUserId() != userId) {
                result = new Result(ResultCodeEnum.FAIL);
                result.setMessage("删除失败！");
                return result;
            }
        }
        result = new Result(ResultCodeEnum.SUCCESS);
        shopCartDao.deleteShopCartByIds(Ids);
        result.setMessage("删除成功！");
        return result;
    }

    @Override
    public Result selectShopCards(int userId) {
        return new Result(ResultCodeEnum.SUCCESS,shopCartDao.findShopCartByUserId(userId));
    }

}
