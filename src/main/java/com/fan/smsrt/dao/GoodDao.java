package com.fan.smsrt.dao;

import com.fan.smsrt.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fan
 * @date 2018/7/21 17:46
 * @Description
 */
@Mapper
@Repository
public interface GoodDao {

    //倒序查询所有商品
    @Select("select * from tb_goods order by goods_id desc")
    List<Goods> selectAllGoods();

    //添加商品
    @Insert("insert into tb_goods (name,store_id,already_sell,money,image_path,details,inventory,type,specification) " +
            "values(#{goods.name},#{goods.storeId},#{goods.alreadySell},#{goods.money},#{goods.imagePath},#{goods.details}," +
            "#{goods.inventory},#{goods.type},#{goods.specification})")
    void insert(@Param("goods") Goods goods);

    //删除商品
    @Delete("delete from tb_goods where goods_id = #{goodsId}")
    void delete(@Param("goodsId") int goodsId);

    //查询所有商品
    @Select("select * from tb_goods where name = #{name}")
    Goods selectByName(@Param("name") String name);

    //根据id查找商品
    @Select("select * from tb_goods where goods_id = #{goodsId}")
    Goods selectById(@Param("goodsId") int goodsId);

    //根据商品id和店铺id查找商品
    @Select("select * from tb_goods where goods_id = #{goodsId} and store_id = #{storeId}")
    Goods selectStoreById(@Param("goodsId") int goodsId,@Param("storeId") int storeId);

    //修改商品
    @Update("update tb_goods set name=#{goods.name},money=#{goods.money}," +
            "inventory = #{goods.inventory},type = #{goods.type},specification = #{goods.specification} " +
            "where goods_id = #{goodsId}")
    void update(@Param("goods") Goods goods,@Param("goodsId") int goodsId);

    //搜索商品
    @Select("select * from tb_goods where name like '%${name}%'")
    List<Goods> searchGoods(@Param("name") String name);

    //分类搜索商品
    @Select("select * from tb_goods where type = #{type}")
    List<Goods> typeSearchGoods(@Param("type") String type);

    //查找店铺商品
    @Select("select * from tb_goods where store_id = #{storeId} order by goods_id desc")
    List<Goods> selectStoreGoods(@Param("storeId") int storeId);

    //查找商品
    @Select("select * from tb_goods where name like '%${name}%' order by goods_id desc")
    List<Goods> selectGoods(@Param("name") String name);

    //订单修改商品
    @Update("update tb_goods set already_sell=#{goods.alreadySell},inventory=#{goods.inventory} " +
            "where goods_id = #{goods.goodsId}")
    void orderUpdate(@Param("goods") Goods goods);
}
