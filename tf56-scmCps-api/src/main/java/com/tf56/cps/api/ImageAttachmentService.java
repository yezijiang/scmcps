package com.tf56.cps.api;

import com.tf56.core.BizReturn;
import com.tf56.core.page.PageResult;
import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;

/**
 * created by yeliming on 2021/2/27 17:27 <br/>
 * 图片附件服务
 */
public interface ImageAttachmentService {

    /**
     * 查询图片附件
     * @return
     */
    BizReturn<PageResult<ImageAttachmentDTO>> queryImageAttachments(ImageAttachmentQTO imageAttachmentQTO);

}
