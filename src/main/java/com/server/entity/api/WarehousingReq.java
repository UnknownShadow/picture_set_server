package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class WarehousingReq {

    @ApiModelProperty(value = "用户ID")
    private List<String> pictures;
    private String title;
    private String imagePath;
}
