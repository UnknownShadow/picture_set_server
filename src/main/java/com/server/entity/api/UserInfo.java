package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserInfo {

    @ApiModelProperty(value = "用户ID")
    private int id;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表132*132正方形头像）")
    private String avatarUrl;
    @ApiModelProperty(value = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private Integer gender;
    @ApiModelProperty(value = "用户所在城市")
    private String city;
    @ApiModelProperty(value = "用户所在省份")
    private String province;
    @ApiModelProperty(value = "用户所在国家")
    private String country;
    @ApiModelProperty(value = "用户的语言，简体中文为zh_CN")
    private String language;
}
