package com.server.entity;


import lombok.Data;

/**
 * 用户信息
 */
@Data
public class UsersEntity {

    //类型1：同步拍；2：悦商城
    public final static int Index_Status = 1;
    public final static int Duty_Freek_Status = 2;

    private int id;
    private String openid;     //小程序唯一标识
    private String nickname;
    private String avatar_url;       //头像
    private int gender;     //性别
    private String city;
    private String province;
    private String country;
    private int address_id;     //默认收货地址
    private int money;          //用户充值的金额（单位：分）
    private int integral;          //积分
    private String created_time;
    private String updated_time;
}

