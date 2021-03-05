package com.tf56.cps.common.util;

import com.google.common.base.Strings;

/**
 * @author : maxx
 * @description：
 * @date ：2019/5/14 10:27
 */
public class StringPersonalUtil {
    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        int len = searchStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }
    public static String assembleString(String... strs) {
      if(null == strs || strs.length == 0 ) return null;
      StringBuilder sb = new StringBuilder();
      for(String str:strs){
          if(!Strings.isNullOrEmpty(str)){
              sb.append(str).append(",");
          }
      }
      if(sb.lastIndexOf(",") >0 ){
          sb.deleteCharAt(sb.lastIndexOf(","));
      }
      return sb.toString();
    }
}
