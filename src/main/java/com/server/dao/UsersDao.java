package com.server.dao;

import com.server.entity.UsersEntity;
import com.server.entity.api.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersDao {

    //根据token查询信息
    @Select("select * from users where token=#{token} limit 1")
    UsersEntity queryByToken(@Param("token") String token);

    //根据ID查询信息
    @Select("select * from users where id=#{id} limit 1")
    UsersEntity queryById(@Param("id") int id);


    //设置默认收货地址
    @Update("update users set address_id = #{address_id} where id = #{id}")
    void updateAddressId(@Param("id") int id, @Param("address_id") int address_id);

    @Select("select IFNULL(name, '')name from users where id = #{id} limit 1")
    String queryNameById(@Param("id") int user_id);


    //查询openid是否存在
    @Select("select exists (select id from users where openid = #{openid} limit 1)")
    int existsOpenId(@Param("openid") String openid);


    //根据openid查询用户信息
    @Select("select * from users where openid = #{openid} limit 1")
    UsersEntity queryByOpenId(@Param("openid") String openid);


    //根据userId查询用户信息
    @Select("select * from users where id = #{user_id} limit 1")
    UsersEntity queryByUserId(@Param("user_id") int user_id);


    //入库
    @Insert("insert into users(openid,nickname,avatar_url,gender,city,province,country,created_time)" +
            "values(#{openId},#{userInfo.nickName},#{userInfo.avatarUrl},#{userInfo.gender},#{userInfo.city}," +
            "#{userInfo.province},#{userInfo.country},now())")
    @Options(useGeneratedKeys = true, keyProperty = "userInfo.id")
    void insertToUsers(@Param("openId") String openId, @Param("userInfo") UserInfo userInfo);

/*@Insert("insert into users(nickname,openid,avatar_url,gender,city,province,country,token,created_time)" +
            "values(#{userInfo.nickName},#{openId},#{userInfo.avatarUrl},#{userInfo.gender},#{userInfo.city}," +
            "#{userInfo.province},#{userInfo.country},#{token},now())")
    @Options(useGeneratedKeys = true, keyProperty = "userInfo.nickName")
    void insertToUsers(@Param("userInfo") UserInfo userInfo, @Param("openId") String openId, @Param("token") String token);
*/


    //增加积分
    @Update("update users set integral = integral + #{integral} where id = #{id}")
    void addIntegral(@Param("integral") int integral, @Param("id") int id);


    //扣除积分
    @Update("update users set integral = integral - #{integral} where id = #{id}")
    void reduceIntegral(@Param("integral") int integral, @Param("id") int id);

}
