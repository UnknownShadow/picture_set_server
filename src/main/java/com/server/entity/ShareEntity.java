package com.server.entity;


import lombok.Data;

@Data
public class ShareEntity {

    private int id;
    private int user_id;        //用户ID
    private int category_id;     //图片组ID
    private int status;             //分享状态码；0：用户取消分享，1：分享成功；2：转发失败
    private String created_time;
    private String updated_time;

}

