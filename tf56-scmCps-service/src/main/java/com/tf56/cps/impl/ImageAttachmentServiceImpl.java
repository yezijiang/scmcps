package com.tf56.cps.impl;

import com.tf56.core.BizReturn;
import com.tf56.core.page.PageResult;
import com.tf56.cps.action.ImageAttachmentQueryAction;
import com.tf56.cps.api.ImageAttachmentService;
import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;
import com.tf56.oms.action.context.Context;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by yeliming on 2021/2/27 17:27 <br/>
 */
@Slf4j
@Service("imageAttachmentService")
public class ImageAttachmentServiceImpl implements ImageAttachmentService {

    @Resource
    private ImageAttachmentQueryAction imageAttachmentQueryAction;

    @Override
    public BizReturn<PageResult<ImageAttachmentDTO>> queryImageAttachments(ImageAttachmentQTO imageAttachmentQTO) {

        /**
         * @see ImageAttachmentQueryAction#before(Context, Object...)
         * @see ImageAttachmentQueryAction#confirming(Context, Object...)
         * @see ImageAttachmentQueryAction#after(Context, BizReturn, Object...)
         */
        BizReturn<PageResult<ImageAttachmentDTO>> bizReturn = this.imageAttachmentQueryAction.execute(imageAttachmentQTO);

        return bizReturn;
    }
}
