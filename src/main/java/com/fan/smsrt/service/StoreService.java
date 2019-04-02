package com.fan.smsrt.service;

import com.fan.smsrt.entity.Store;
import com.fan.smsrt.util.result.Result;

public interface StoreService {

    Result createStore(Store store, int userId);

    Result selectStore(int storeId);
}
