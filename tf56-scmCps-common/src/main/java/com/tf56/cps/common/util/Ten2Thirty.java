package com.tf56.cps.common.util;

/**
 * @author : matthew
 * @description :
 * @date : 2021/3/1 1:39 下午
 **/
public class Ten2Thirty {
    private static final Integer SUPPLIER_CODE_LENGTH = 6;
    private static final String[] X36_ARRAY = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public static String tenTo36(Long num) {
        StringBuffer sBuffer = new StringBuffer();

        if(num == 0) {
            sBuffer.append("0");
        }
        while(num > 0) {
            Long i = num % 36;
            sBuffer.append(X36_ARRAY[i.intValue()]);
            num = num / 36;
        }

        return sBuffer.reverse().toString();
    }

    public static String getCode6Length(Long id){
        String idThrity = tenTo36(id);
        switch (idThrity.length() % 6) {
            case 0:
                return idThrity;
            case 1:
                return "00000" + idThrity;
            case 2:
                return "0000" + idThrity;
            case 3:
                return "000" + idThrity;
            case 4:
                return "00" + idThrity;
            case 5:
                return "0" + idThrity;
            default:
                return "";
        }
    }

    public static String getCode6Length(String prefix ,Long id){
        return prefix+getCode6Length(id);
    }
}
