package com.tf56.cps.api.qto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * created by yeliming on 2018/7/17 10:06 <br/>
 */
@Setter
@Getter
@ToString
public class BaseQTO implements Serializable {
    private static final long serialVersionUID = -5117636741536594254L;
    /**
     * 不需要分页吗，约定：true：不分页，NULL
     */
    private Boolean noNeedPaging;
    
    private Integer pageNo = 1;

    private Integer offset = 0;

    private Integer pageSize = 10000;

    private String orderBy;
    
    private String orderByType;
    
    /**
     * 商户的会员id
     */
    private Long mainPartyId;

}
