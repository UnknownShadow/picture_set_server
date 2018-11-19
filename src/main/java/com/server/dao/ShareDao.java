package com.server.dao;

import com.server.entity.CategoryEntity;
import com.server.entity.api.CategoryResp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShareDao {

    //数据新增
    @Insert("insert into `share`(user_id,category_id,status,created_time)" +
            "values(#{user_id},#{category_id},#{status},now())")
    void insert(@Param("user_id") int user_id, @Param("category_id") int category_id,
                @Param("status") int status);


}
