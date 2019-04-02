package com.fan.smsrt.dao;

import com.fan.smsrt.entity.ShopCart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopCartDao {

    /**
     * 根据商品id和用户id查询购物车
     * @param goodsId
     * @param userId
     */
    @Select("select * from tb_shop_cart where goods_id = #{goodsId} and user_id = #{userId}")
    ShopCart selectShopCard(@Param("goodsId") int goodsId, @Param("userId") int userId);

    /**
     * 添加购物车
     * @param shopCart
     */
    @Insert("INSERT INTO tb_shop_cart (goods_id,user_id,store_id,store_name,goods_name,image_path," +
            "money,specification,goods_count,build_date) " +
            "VALUES " +
            "(#{shopCart.goodsId},#{shopCart.userId},#{shopCart.storeId},#{shopCart.storeName}," +
            "#{shopCart.goodsName},#{shopCart.imagePath},#{shopCart.money},#{shopCart.specification}," +
            "#{shopCart.goodsCount},#{shopCart.buildDate})")
    void insertShopCard(@Param("shopCart") ShopCart shopCart);

    /**
     * 更新购物车数量
     * @param shopCart
     */
    @Update("UPDATE tb_shop_cart SET goods_count = #{shop_cart.goodsCount} " +
            "WHERE shop_cart_id = #{shop_cart.shopCartId} ")
    void updateShopCartCount(@Param("shop_cart") ShopCart shopCart);

    /**
     * 通过购物车id查找购物车
     * @param shopCartId
     */
    @Select("SELECT * FROM tb_shop_cart WHERE shop_cart_id = #{shop_cart_id}")
    ShopCart findShopCartById(@Param("shop_cart_id") int shopCartId);

    /**
     * 根据购物车id删除购物车商品
     * @param shopCartId
     */
    @Delete("DELETE FROM tb_shop_cart WHERE shop_cart_id = #{shop_cart_id}")
    void deleteShopCartById(@Param("shop_cart_id") int shopCartId);

    /**
     * 通过购物车ids数组查找购物车
     * @param strList
     */
    @Select("<script>"+
            "SELECT * FROM tb_shop_cart WHERE shop_cart_id IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<ShopCart> findShopCartByIds(@Param("strList") List<String> strList);

    /**
     * 根据购物车id数组删除购物车商品
     * @param strList
     */
    @Delete("<script>"+
            "DELETE FROM tb_shop_cart WHERE shop_cart_id IN "
            + "<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    void deleteShopCartByIds(@Param("strList") List<String> strList);

    @Select("SELECT * FROM tb_shop_cart WHERE user_id = #{user_id}")
    List<ShopCart> findShopCartByUserId(@Param("user_id") int userId);

    @Delete("DELETE FROM tb_shop_cart WHERE goods_id = #{goods_id} AND user_id = #{user_id}")
    void deleteShopCartByIdAndUserId(@Param("goods_id") int goodsId, @Param("user_id") int userId);
}
