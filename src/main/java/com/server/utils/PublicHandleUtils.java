package com.server.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 公共处理工具类
 */
public class PublicHandleUtils {

    public static Logger logger = LoggerFactory.getLogger(PublicHandleUtils.class);

    /**
     * 从配置文件中获取对应的cosPath
     *
     * @param cosPaths cosPath配置参数
     * @param status   状态码
     * @return
     */
    public static String getCosPath(String cosPaths, int status) {

        //默认cosPath
        String cosPath = "/client-img/";

        JSONObject jsonObjects = JSONObject.parseObject(cosPaths);
        JSONArray path = jsonObjects.getJSONArray("path");
        for (Object o : path) {
            JSONObject json = JSONObject.parseObject(o + "");
            int status_value = json.getIntValue("status");
            if (status_value == status) {
                cosPath = json.getString("cosPath");
            }
        }
        logger.info("cosPath值：{}", cosPath);

        return cosPath;
    }


    //获取时间戳
    public static String getTimeStamp() {
        long timeStamp = System.currentTimeMillis();
        long stamp = timeStamp / 1000;
        return stamp + "";
    }


    /**
     * 根据 新品、价格、收藏量 排序
     *
     * @param order_status 排序状态码；1：默认排序、新品；2：价格升序；3：价格降序；4：收藏量
     * @return
     */
    public static String getOrder(int order_status) {
        String order = "created_time desc";
        switch (order_status) {
            case 2:
                order = "price";
                break;
            case 3:
                order = "price desc";
                break;
            case 4:
                order = "collection_num desc";
                break;
        }
        return order;
    }


    /**
     * 根据 商品ID 查询 SKU 并根据SKU索引找到 用户购买的SKU值
     *
     * @param sku_index SKU索引值
     * @param attribute 商品SKU
     * @return
     */
    public static JSONObject getSKU(int sku_index, String attribute) throws Exception {

        //转换为数组
        JSONArray attrs = JSONObject.parseArray(attribute);
        if (PublicUtil.isNotEmpty(attrs)) {
            for (Object attr : attrs) {
                JSONObject json = JSONObject.parseObject(attr + "");
                int index = json.getIntValue("index");
                if (index == sku_index) {
                    return json;
                }
            }
        }
        return null;
    }



}
