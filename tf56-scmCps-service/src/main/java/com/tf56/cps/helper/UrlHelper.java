package com.tf56.cps.helper;

import org.apache.logging.log4j.util.Strings;

/**
 * @author : matthew
 * @description :
 * @date : 2021/3/3 3:26 下午
 **/
public class UrlHelper {
    public final static String GROUP1 ="group1";
    public final static String GROUP2 ="group2";
    public final static String SPECIAL_SPLIT ="?";

    public static String interceptUrl(String imageUrl){
        if(Strings.isBlank(imageUrl)){
            return "";
        }
        int start1 = imageUrl.indexOf(GROUP1);
        if(start1>0){
            return imageUrl.substring(start1);
        }

        int start2 = imageUrl.indexOf(GROUP2);
        if(start2>0){
            return imageUrl.substring(start2);
        }
        return imageUrl;
    }
    public static String deleteDoggy(String imageUrl){
        String imageDoggyUrl = interceptUrl(imageUrl);
        if(Strings.isBlank(imageDoggyUrl)){
            return "";
        }
        int index = imageDoggyUrl.indexOf(SPECIAL_SPLIT);
        if(index>0){
            return imageDoggyUrl.substring(0,index);
        }
        return imageDoggyUrl;
    }
}
