package com.tf56.cps.action;

import com.tf56.core.BizReturn;
import com.tf56.core.exception.BizException;
import com.tf56.core.page.PageResult;
import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;
import com.tf56.cps.common.response.ResponseCode;
import com.tf56.cps.manager.ImageAttachmentManager;
import com.tf56.oms.action.AbstractAction;
import com.tf56.oms.action.Action;
import com.tf56.oms.action.DefaultGroup;
import com.tf56.oms.action.context.Context;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.List;

/**
 * created by yeliming on 2021/3/1 09:04 <br/>
 */
@Slf4j
@Service
public class ImageAttachmentQueryAction extends AbstractAction<BizReturn<PageResult<ImageAttachmentDTO>>> {

    @Resource
    private ImageAttachmentManager imageAttachmentManager;

    @Override
    protected Action action() {
        return CpsActionEnum.图片附件查询;
    }

    @Override
    protected void before(Context context, Object... args) {

    }

    @Override
    protected void validated(Context context, Validator validator, Class<? extends DefaultGroup>[] groups, Object... args) {
        super.validated(context, validator, groups, args);

        ImageAttachmentQTO imageAttachmentQTO = (ImageAttachmentQTO) args[0];
        String imageSource = imageAttachmentQTO.getImageSource();
        List<String> imageSourceList = imageAttachmentQTO.getImageSourceList();

        String imageType = imageAttachmentQTO.getImageType();
        List<String> imageTypeList = imageAttachmentQTO.getImageTypeList();

        if (StringUtils.isBlank(imageSource) && CollectionUtils.isEmpty(imageSourceList)) {
            log.warn("图片来源和图片来源列表不能同时为空");
            throw new BizException(ResponseCode.CPS_PARAM_NULL, "图片来源和图片来源列表不能同时为空");
        }
        if (StringUtils.isBlank(imageType) && CollectionUtils.isEmpty(imageTypeList)) {
            log.warn("图片附件类型和图片附件类型列表不能同时为空");
            throw new BizException(ResponseCode.CPS_PARAM_NULL, "图片附件类型和图片附件类型列表不能同时为空");
        }
    }

    @Override
    protected BizReturn<PageResult<ImageAttachmentDTO>> confirming(Context context, Object... args) {
        ImageAttachmentQTO imageAttachmentQTO = (ImageAttachmentQTO) args[0];
        List<ImageAttachmentDTO> attachmentDTOs = this.imageAttachmentManager.queryImageAttachmentList(imageAttachmentQTO);
        int size = attachmentDTOs.size();
        PageResult<ImageAttachmentDTO> pageResult = new PageResult<>(size, attachmentDTOs);
        return BizReturn.create(pageResult);
    }

    @Override
    protected void cancelling(Context context, Exception e, Object... args) {

    }

    @Override
    protected void after(Context context, BizReturn<PageResult<ImageAttachmentDTO>> bizReturn, Object... args) {

    }
}
