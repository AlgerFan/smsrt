package com.fan.smsrt.service.serviceimpl;

import com.fan.smsrt.dao.FootprintDao;
import com.fan.smsrt.dao.GoodDao;
import com.fan.smsrt.dao.StoreDao;
import com.fan.smsrt.entity.Footprint;
import com.fan.smsrt.entity.Goods;
import com.fan.smsrt.entity.Store;
import com.fan.smsrt.service.FootprintService;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 阿杰
 * @create 2018-07-28 10:04
 * @Description:
 */
@Service
public class FootprintServiceImpl implements FootprintService {
    @Resource
    private FootprintDao footprintDao;
    @Resource
    private StoreDao storeDao;
    @Resource
    private GoodDao goodDao;

    @Override
    public Result createFootprint(int userId, int goodsId) {
        Date date = new Date();
        Store store = storeDao.findStore(userId);
        Goods goods = goodDao.selectById(goodsId);
        Footprint footprint = new Footprint(userId,goodsId,goods.getName(),goods.getMoney(),goods.getImagePath(),store.getStoreName(),date);
        footprintDao.insert(footprint);
        return new Result(ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result findAllFootprint(int userId) {
        return new Result(ResultCodeEnum.SUCCESS,footprintDao.findAllFootprint(userId));
    }

    @Override
    public Result deleteFootprint(int footprintId) {
        footprintDao.deleteFootprint(footprintId);
        return new Result(ResultCodeEnum.SUCCESS);
    }
}
