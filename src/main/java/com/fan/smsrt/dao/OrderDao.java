package com.fan.smsrt.dao;

import com.fan.smsrt.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {

    @Insert("INSERT INTO tb_order (user_id,user_name,order_status,order_date,phone,address,count,goods_name,image_path,money,specification) " +
            "VALUES (#{order.userId},#{order.userName},#{order.orderStatus},#{order.orderDate}," +
            "#{order.phone},#{order.address},#{order.count},#{order.goodsName},#{order.imagePath},#{order.money},#{order.specification})")
    void saveOrder(@Param("order") Order order);

    //订单修改状态
    @Update("update tb_order set order_status=#{order.orderStatus} " +
            "where order_id = #{order.orderId}")
    void orderUpdate(@Param("order") Order order);

    @Select("SELECT * FROM tb_order WHERE user_id = #{user_id} AND order_id = #{order_id}")
    Order findOrderById(@Param("user_id") int userId, @Param("order_id") int orderId);

    @Select("SELECT * FROM tb_order WHERE user_id = #{user_id}")
    List<Order> getAll(@Param("user_id") int userId);

    @Select("SELECT * FROM tb_order WHERE user_id = #{user_id} AND order_status = #{order_status}")
    List<Order> getAllByStatus(@Param("user_id") int userId, @Param("order_status") String status);

    @Select("SELECT * FROM tb_order WHERE order_date = #{order.orderDate} AND user_id = #{order.userId}")
    Order getOrderByDateAndUserId(@Param("order") Order order);

    @Select("SELECT * FROM tb_order WHERE user_id = #{storeId}")
    List<Order> selectStoreOrder(@Param("storeId") int storeId);

}
