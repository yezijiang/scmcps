package com.tf56.cps.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 715894831120885260L;

    private Integer updateVersion;
    private Date gmtCreate;
    private Date gmtModified;
    /** 删除标志位 */
    private Boolean isDeleted;
    /** 创建者会员ID */
    private Integer createOperatorId;
    /** 创建者会员名称 */
    private String createOperator;
    /** 更新者会员ID */
    private Integer modifyOperatorId;
    /** 更新者会员名称 */
    private String modifyOperator;
    /** 所属者会员ID */
    private Integer ownerPartyId;

}


