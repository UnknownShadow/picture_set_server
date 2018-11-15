package com.server.dao;

import com.alibaba.fastjson.JSONObject;
import com.server.entity.PicturesEntity;
import com.server.entity.api.CategoryResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PicturesDao {

    @Select("select img from pictures where category_id = #{category_id}")
    List<JSONObject> queryByCategoryID(@Param("category_id") int category_id);

}
