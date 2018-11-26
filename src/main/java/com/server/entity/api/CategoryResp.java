package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CategoryResp {

    @ApiModelProperty(value = "用户ID")
    private int id;
    @ApiModelProperty(value = "首页展示图")
    private String headImg;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "总点赞数")
    private int thumbsUp;

}
