package com.tf56.cps.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kay
 * @version v1.0
 */
@Data
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = -5577195838694720488L;

    /**
     * 创建时间 inputDate
     */
    private Date gmtCreate;

    /**
     * 创建人 inputMan
     */
    private String createMan;

    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 更新人
     */
    private String updateMan;
    /**
     * 更新时间 updateDate
     */
    private Date gmtModified;
    /**
     * 删除标志位
     */
    private Boolean isDeleted;

    /**
     * 日志前缀
     */
    private String logPre;

    /**
     * 创建者会员ID
     */
    private Integer createOperatorId;
    /**
     * 创建者会员名称
     */
    private String createOperator;
    /**
     * 更新者会员ID
     */
    private Integer modifyOperatorId;
    /**
     * 更新者会员名称
     */
    private String modifyOperator;
    /**
     * 所属者会员ID
     */
    private Integer ownerPartyId;

}


