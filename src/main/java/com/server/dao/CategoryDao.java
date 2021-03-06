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

    @Select("select id,head_img headImg,title,pv thumbsUp,date_format(created_time,'%Y-%m-%d %H:%i') date " +
            "from category where show_status != 0 order by created_time desc limit #{offset},#{limit}")
    List<CategoryResp> getAllByPaging(@Param("offset") int offset,@Param("limit") int limit);


    //数据新增
    @Insert("insert into category(head_img,title,created_time)values(#{head_img},#{title},now())")
    @Options(useGeneratedKeys = true,keyProperty = "categoryEntity.id")
    void insert(@Param("head_img") String head_img,@Param("title") String title,
                @Param("categoryEntity") CategoryEntity categoryEntity);

    //增加访问量
    @Update("update category set pv=pv+1 where id = #{id}")
    void updatePvByID(@Param("id") int id);


}
