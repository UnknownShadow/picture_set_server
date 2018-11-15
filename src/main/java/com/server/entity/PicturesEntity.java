package com.server.entity;


import lombok.Data;

@Data
public class PicturesEntity {

    private int id;
    private String img;
    private int category_id;        //一级类目关联ID
    private String created_time;

}

