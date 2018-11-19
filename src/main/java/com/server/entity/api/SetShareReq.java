package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SetShareReq {

    @ApiModelProperty(value = "用户ID")
    private int id;
    @ApiModelProperty(value = "图片组ID")
    private int categoryId;
    @ApiModelProperty(value = "标题")
    private int status;

}
