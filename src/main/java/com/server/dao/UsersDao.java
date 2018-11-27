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


    //增加访问次数
    @Update("update users set times = times + 1,updated_time=now() where id=#{id}")
    void updateTimesByID(@Param("id") int id);


    //当日新增人数
    @Select("select count(id) from users where created_time between #{start_time} and #{end_time}")
    int countByToday(@Param("start_time")String start_time,@Param("end_time")String end_time);


    //总人数
    @Select("select count(id) from users")
    int countAllUser();

}
