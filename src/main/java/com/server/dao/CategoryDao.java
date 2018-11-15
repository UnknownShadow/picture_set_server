package com.server.dao;

import com.server.entity.CategoryEntity;
import com.server.entity.UsersEntity;
import com.server.entity.api.CategoryResp;
import com.server.entity.api.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryDao {

    @Select("select id,head_img headImg,title,thumbs_up thumbsUp " +
            "from category order by created_time desc limit #{offset},#{limit}")
    List<CategoryResp> getAllByPaging(@Param("offset") int offset,@Param("limit") int limit);

}
