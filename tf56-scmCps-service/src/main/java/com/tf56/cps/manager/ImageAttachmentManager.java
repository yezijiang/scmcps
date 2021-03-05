package com.tf56.cps.manager;

import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;

import java.util.List;

/**
 * created by yeliming on 2021/3/1 15:48 <br/>
 */
public interface ImageAttachmentManager {

    /**
     * 查询图片附件列表
     *
     * @param imageAttachmentQTO
     * @return
     */
    List<ImageAttachmentDTO> queryImageAttachmentList(ImageAttachmentQTO imageAttachmentQTO);

}
