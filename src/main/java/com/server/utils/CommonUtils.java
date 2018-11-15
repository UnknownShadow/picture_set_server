package com.server.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2018/5/31.
 */
public class CommonUtils {

    /**
     * 创建单号
     *
     * @return
     */
    public static String createOutTradeNo() {
        String sysDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//设置日期格式

        String deal = sysDate.replaceAll("-", "");
        String with = deal.replaceAll(":", "");
        String dealWith = with.replaceAll(" ", "");

        int frontRandom = (int) (Math.random() * 9000 + 1000);
        int afterRandom = (int) (Math.random() * 9000 + 1000);

        String orderNumber = frontRandom + dealWith + afterRandom;

        return orderNumber;
    }


    /**
     * 合并两个JSONObject
     *
     * @param source
     * @param target
     * @return
     * @throws JSONException
     */
    public static JSONObject deepMerge(JSONObject source, JSONObject target) throws JSONException {

        for (String key : source.keySet()) {
            Object value = source.get(key);
            if (!target.containsKey(key)) {
                // new value for "key":
                target.put(key, value);
            } else {
                // existing value for "key" - recursively deep merge:
                if (value instanceof JSONObject) {
                    JSONObject valueJson = (JSONObject) value;
                    deepMerge(valueJson, target.getJSONObject(key));
                } else {
                    target.put(key, value);
                }
            }
        }
        return target;
    }


    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static int TransformationTimeStamp(Date date) throws ParseException {
        return (int) (((Date) date).getTime() / 1000);
    }

    public static int TransformationTimeStamp(String date, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date dateStart = format.parse(date);
        return (int) (dateStart.getTime() / 1000);
    }


    /**
     * 随机生成自义定长度的字符串（大写+小写+数字）
     *
     * @param length 需要生成的长度
     * @return
     */
    public static String getRandomString(int length) {
        //随机字符串的随机字符库
        String keyString = "0123456789";
        int len = keyString.length();

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < length; i++) {
            buffer.append(keyString.charAt((int) (Math.round(Math.random() * (len - 1)))));
        }

        return buffer.toString();
    }


}
