package com.tf56.cps.biz;

import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;
import com.tf56.cps.domain.ImageAttachmentDO;

import java.util.List;

/**
 * created by yeliming on 2021/3/1 10:26 <br/>
 * 内部的图片附件服务
 */
public interface ImageAttachmentBizService {

    /**
     * 批量保存图片附件
     */
    int batchInsert(List<ImageAttachmentDTO> imageAttachmentDTOs);

    /**
     * 查询图片附件列表
     */
    List<ImageAttachmentDO> queryImageAttachmentList(ImageAttachmentQTO imageAttachmentQTO);

    /**
     * 删除图片附件
     */
    int delete(List<Long> imageAttachmentIdList, Integer modifyOperatorId, String modifyOperator);
}
