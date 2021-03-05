package com.tf56.cps.api.qto;

import com.tf56.cps.api.group.ImageAttachmentQueryGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * created by yeliming on 2021/2/27 18:21 <br/>
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ImageAttachmentQTO extends BaseQTO{
    /**
     * 主键ID
     */
    private Long imageAttachmentId;
    /**
     * 供应商code
     */
    @NotBlank(message = "供应商code不能为空", groups = ImageAttachmentQueryGroup.class)
    private String supplierCode;
    /**
     * 图片附件类型，供应商/法人正面
     */
    private String imageType;

    /**
     * 图片附件类型列表
     */
    private List<String> imageTypeList;
    /**
     * 图片来源，会员/运营平台
     */
    private String imageSource;
    /**
     * 图片来源列表
     */
    private List<String> imageSourceList;
}
