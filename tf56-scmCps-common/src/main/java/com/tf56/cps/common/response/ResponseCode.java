package com.tf56.cps.common.response;

import com.tf56.core.exception.ErrorCodeInterface;

/**
 * created by yeliming on 2021/3/1 11:03 <br/>
 */
public enum ResponseCode implements ErrorCodeInterface {
    CPS_SUCCESS(0, "请求成功"),
    CPS_SYSTEM_ERROR(-20001, "网络异常"),
    CPS_PARAM_NULL(-20002, "参数为空"),
    CPS_PARAM_INVALID(-20003, "参数不合法"),
    CPS_NO_DATA_AUTH(-20004, "权限非法"),
    CPS_OUTER_ERROR(-20005, "外部系统网络异常"),
    CPS_REPEAT_OPERATION(-20006, "重复提交"),

    CPS_INSERT_ERROR(-20101, "新增失败"),
    CPS_UPDATE_ERROR(-20102, "更新失败"),
    CPS_DELETE_ERROR(-20103, "删除失败"),
    CPS_QUERY_ERROR(-20104, "查询失败"),
    ;

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}


