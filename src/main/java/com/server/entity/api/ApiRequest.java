package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ApiModel
public class ApiRequest implements Serializable {

    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty(value = "当前页数")
    protected int page;
    @ApiModelProperty(value = "每页大小")
    protected int limit;

}
