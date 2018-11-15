package com.server.entity.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel
public class ActivityModule implements Serializable {

    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "模块描述")
    private String desc;
    @ApiModelProperty(value = "展示图片路径")
    private String img;
    @ApiModelProperty(value = "资源类型1：图片；2：视频")
    private int type;
    @ApiModelProperty(value = "0：不展示；1：展示")
    private int status;
    @ApiModelProperty(value = "创建时间")
    private String createdTime;

}
