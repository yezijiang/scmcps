package com.tf56.cps.action;

import com.tf56.cps.api.group.*;
import com.tf56.oms.action.Action;
import com.tf56.oms.action.DefaultGroup;

/**
 * created by yeliming on 2021/3/1 09:07 <br/>
 */
public enum CpsActionEnum implements Action {
    图片附件查询("图片附件查询", ImageAttachmentQueryGroup.class),
    ;

    private final String actionName;
    private final Class<? extends DefaultGroup>[] groups;

    @SafeVarargs
    CpsActionEnum(String actionName, Class<? extends DefaultGroup>... groups) {
        this.actionName = actionName;
        this.groups = groups;
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

    @Override
    public Class<? extends DefaultGroup>[] groups() {
        return this.groups;
    }
}
