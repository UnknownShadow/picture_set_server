package com.server.utils;

/**
 * Created by juunew on 2017/8/30.
 */

import java.security.MessageDigest;
import java.util.regex.Pattern;

public class MD5Util {
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     **/
    public static String getMessageDigest(String data) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));

            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString().toUpperCase();
    }

    //
    public static boolean isDateHeFa(String date) {
        if (isBlank(date) || date.length() != 19)
            return false;
        final Pattern pattern = Pattern.compile("^\\d{4}(-\\d{2}){2}\\s\\d{2}(:\\d{2}){2}");
        return pattern.matcher(date).matches();
    }

    public static boolean isBlank(String str){
        return null == str || "".equals(str);
    }

    public static void main(String[] args) {
        boolean dateHeFa = isDateHeFa("2018-10-11 17:52:41");
        System.out.println(dateHeFa);
    }



}