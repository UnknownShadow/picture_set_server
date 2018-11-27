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
public interface PreviewRecordDao {

    //数据新增
    @Insert("insert into preview_record(user_id,pictures_id,created_time)values(#{user_id},#{pictures_id},now())")
    void insert(@Param("user_id") int user_id, @Param("pictures_id") int pictures_id);

}
