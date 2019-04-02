package com.fan.smsrt.service.serviceimpl;

import com.fan.smsrt.dao.StoreDao;
import com.fan.smsrt.dao.UserDao;
import com.fan.smsrt.entity.Store;
import com.fan.smsrt.entity.User;
import com.fan.smsrt.service.StoreService;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 阿杰
 * @create 2018-07-25 16:12
 * @Description:
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreDao storeDao;
    @Resource
    private UserDao userDao;

    @Override
    public Result createStore(Store store, int userId) {
        Result result;
        Store userIs = storeDao.findStore(userId);
        if(store.getStoreName()==null || store.getStoreIntroduction()==null){
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("请填写完整信息!");
            return result;
        }
        if(userIs!=null){
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("您已经创建过店铺！");
            return result;
        }
        User user = userDao.selectUserById(userId);
        store.setUserName(user.getUserName());
        store.setStoreId(userId);
        storeDao.createStore(store);
        user.setStoreId(userId);
        user.setStatus(1);
        userDao.update(user);
        result = new Result(ResultCodeEnum.SUCCESS,store);
        result.setMessage("创建店铺成功");
        return result;
    }

    @Override
    public Result selectStore(int storeId) {
        return new Result(ResultCodeEnum.SUCCESS,storeDao.findStore(storeId));
    }
}
