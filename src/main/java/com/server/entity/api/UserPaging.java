package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString(includeFieldNames = true,callSuper = true)
public class UserPaging extends Paging{

    @ApiModelProperty(value = "用户ID")
    private Integer id;

}
