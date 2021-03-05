package com.tf56.cps.util;

import org.apache.logging.log4j.util.Strings;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by herb on 2020/5/22 8:42
 */
public class FastDfsCpsGroup2Util {

    private final static String FASTDFS_URL = ConfigUtil.getValue("config/fastdfs.properties", "web.fastDFs.url");

    private final static String DOGGY_AK = ConfigUtil.getValue("config/fastdfs.properties", "doggy.ak");

    private final static String DOGGY_SK = ConfigUtil.getValue("config/fastdfs.properties", "doggy.sk");

    public static String addSign4Url(String url){
        if(Strings.isBlank(url)){
            return url;
        }
        return FASTDFS_URL + url + addSign4Url(url, DOGGY_AK, DOGGY_SK);
    }
    /**
     * url:图片url
     * dog_ak: doggy参数.
     * dog_sk: doggy参数
     * return:加签后的url
     */
    public static String addSign4Url(String url, String dog_ak, String dog_sk) {
        String[] urls = url.split("/");
        String doggyurl = urls[urls.length - 1];
        String[] urlend =  doggyurl.split("\\.");
        doggyurl = urlend[0];
        Map<String, String> map = new HashMap<String, String>();
        String timestamp = getCurrentTime4Doggy();
        map.put("tf_timestamp", timestamp);
        map.put("dog_ak", dog_ak);
        map.put("dog_sk", dog_sk);
        map.put("doggyurl", doggyurl);
        String sign = map2MD5(map);
        map.put("tf_sign", sign);
        StringBuilder sb = new StringBuilder();
        String realurl = sb.append("?")
                .append("doggyurl=").append(doggyurl)
                .append("&dog_ak=")
                .append(dog_ak)
                .append("&tf_sign=").append(sign)
                .append("&tf_timestamp=").append(timestamp).toString();
        return  realurl;
    }
    private static String getCurrentTime4Doggy() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }
    private static String map2MD5(Map<String, String> map) {
        Object[] key = map.keySet().toArray();
        Arrays.sort(key);
        StringBuilder sb = new StringBuilder();
        for (int i = key.length - 1; i >= 0; i--) {
            sb.append(map.get(key[i]));
        }
        try {
            String keyString = sb.toString();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(keyString.getBytes(StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            byte[] temp;
            temp = md5.digest("".getBytes(StandardCharsets.UTF_8));
            for (byte b : temp) {
                result.append(Integer.toHexString((0x000000ff & b) | 0xffffff00).substring(6));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            //TODO 错误日志
        }
        return "";
    }
}
