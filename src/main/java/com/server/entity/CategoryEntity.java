package com.server.entity;


import lombok.Data;

@Data
public class CategoryEntity {

    private int id;
    private String head_img;
    private String title;
    private int thumbs_up;          //总点赞数
    private String created_time;

}

