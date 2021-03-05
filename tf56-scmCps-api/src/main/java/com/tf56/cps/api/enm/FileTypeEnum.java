package com.tf56.cps.api.enm;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : matthew
 * @description :
 * @date : 2021/3/1 4:35 下午
 **/
@Getter
public enum FileTypeEnum {
    法人身份证正面("juridicalIdCardPicFront", "法人身份证正面"),
    法人身份证反面("juridicalIdCardPicBack", "法人身份证反面"),
    营业执照("businessLicencePic", "营业执照"),
    道路经营许可证("roadPermitPic", "道路经营许可证");
    private String fieldName;
    private String name;

    FileTypeEnum(String fieldName, String name) {
        this.fieldName = fieldName;
        this.name = name;
    }

    private static final Map<String, String> map = new HashMap<>();

    static {
        for (FileTypeEnum fileTypeEnum : FileTypeEnum.values()) {
            String key = fileTypeEnum.getFieldName();
            String value = fileTypeEnum.getName();
            map.put(key, value);
        }
    }

    public static String getByFieldName(String fieldName) {
        return map.get(fieldName);
    }


}
