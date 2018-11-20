package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SetPreviewReq {

    @ApiModelProperty(value = "用户ID")
    private int id;
    @ApiModelProperty(value = "图片单张图片ID")
    private int picturesId;

}
