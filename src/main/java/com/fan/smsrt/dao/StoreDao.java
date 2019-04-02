package com.fan.smsrt.dao;

import com.fan.smsrt.entity.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StoreDao {

    @Insert("insert into tb_store (store_id,user_name,store_name,store_introduction,user_id_card,day_sales,month_sales,day_clicks,total_sales) " +
            "values(#{store.storeId},#{store.userName},#{store.storeName},#{store.storeIntroduction},#{store.userIdCard},#{store.daySales}," +
            "#{store.monthSales},#{store.dayClicks},#{store.totalSales})")
    void createStore(@Param("store") Store store);

    @Select("select * from tb_store where store_id = #{store_id}")
    Store findStore(@Param("store_id") int store_id);

}
