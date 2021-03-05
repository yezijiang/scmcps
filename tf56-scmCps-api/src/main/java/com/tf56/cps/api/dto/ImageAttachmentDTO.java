package com.tf56.cps.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * created by yeliming on 2021/2/27 18:15 <br/>
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ImageAttachmentDTO extends BaseDTO implements Cloneable{
    /**
     * 图片附件id
     */
    private Long imageAttachmentId;

    /**
     * 供应商code
     */
    private String supplierCode;

    /**
     * 图片来源，会员/运营平台
     */
    private String imageSource;

    /**
     * 图片附件类型，供应商/法人正面
     */
    private String imageType;

    /**
     * 图片url
     */
    private String imageUrl;

    @Override
    public ImageAttachmentDTO clone() throws CloneNotSupportedException {
        return (ImageAttachmentDTO) super.clone();
    }
}