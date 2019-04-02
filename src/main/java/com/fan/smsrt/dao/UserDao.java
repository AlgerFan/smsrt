package com.fan.smsrt.dao;

import com.fan.smsrt.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AlgerFan
 * @date 2018/7/10 13:43
 * @Description
 */
@Mapper
@Repository
public interface UserDao {

    /**
     *  通过id查找用户
     * @param id 用户id
     */
    @Select("select * from tb_user where user_id = #{user_id}")
    User selectUserById(@Param("user_id")int id);

    /**
     * 检查用户名是否存在
     * @param userName
     */
    @Select("select * from tb_user where user_name = #{user_name}")
    User checkUserName(@Param("user_name")String userName);

    /**
     * 检查手机号是否存在
     * @param phone
     */
    @Select("select * from tb_user where phone = #{phone}")
    User checkPhone(@Param("phone") String phone);

    /**
     * 注册
     * @param user
     */
    @Insert("INSERT INTO tb_user (user_name,password,phone) VALUES (#{user.userName},#{user.password},#{user.phone})")
    void resister(@Param("user") User user);

    /**
     * 更新用户个人信息
     * @param user
     */
    @Update("UPDATE tb_user SET nick_name=#{user.nickname},email=#{user.email},phone=#{user.phone}," +
            "accept_address=#{user.acceptAddress},city=#{user.city} WHERE user_id=#{user.userId}")
    void updateUser(@Param("user") User user);

    /**
     * 修改用户密码
     * @param user
     */
    @Update("UPDATE tb_user SET password=#{user.password} WHERE user_id=#{user.userId}")
    void updatePassword(@Param("user") User user);

    /**
     *  更新用户店铺id
     * @param user
     */
    @Update("UPDATE tb_user SET status=#{user.status}, store_id=#{user.userId} WHERE user_id=#{user.userId}")
    void update(@Param("user") User user);

    /**
     * 查询所有用户
     */
    @Select("select * from tb_user where user_name like '%${user_name}%'")
    List<User> selectAllUser(@Param("user_name") String user_name);

    /**
     * 后台添加用户
     * @param user
     */
    @Insert("INSERT INTO tb_user (nick_name,user_name,password,phone,email,city,role_id) " +
            "VALUES (#{user.nickname},#{user.userName},#{user.password},#{user.phone},#{user.email},#{user.city},#{user.roleId})")
    void insertUser(@Param("user") User user);

    /**
     * 后台通过id删除用户
     * @param userId
     */
    @Delete("delete from tb_user where user_id = #{userId}")
    void deleteById(@Param("userId") int userId);

    /**
     * 后台修改用户信息
     * @param user
     */
    @Update("UPDATE tb_user SET nick_name=#{user.nickname},user_name=#{user.userName},password=#{user.password}," +
            "phone=#{user.phone},email=#{user.email},city=#{user.city},role_id=#{user.roleId} WHERE user_id=#{user.userId}")
    void adminUpdateUser(@Param("user") User user);
}
