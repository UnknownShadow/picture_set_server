package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@ApiModel
public class Paging implements Serializable {

    @ApiModelProperty(value = "当前页数")
    protected Integer page;
    @ApiModelProperty(value = "每页大小")
    protected Integer limit;
}
