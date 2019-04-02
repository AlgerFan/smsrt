package com.fan.smsrt.dao;

import com.fan.smsrt.entity.Footprint;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FootprintDao {

    @Insert("insert into tb_footprint (user_id,goods_id,name,money,image_path,store_name,footprint_time) " +
            "values(#{footprint.userId},#{footprint.goodsId},#{footprint.name},#{footprint.money},#{footprint.imagePath}," +
            "#{footprint.storeName},#{footprint.footprintTime})")
    void insert(@Param("footprint") Footprint footprint);

    @Select("select * from tb_footprint where user_id = #{userId}")
    List<Footprint> findAllFootprint(@Param("userId") int userId);

    @Delete("delete from tb_footprint where footprint_id = #{footprintId}")
    void deleteFootprint(@Param("footprintId") int footprintId);
}
