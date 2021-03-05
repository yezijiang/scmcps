package com.tf56.cps.mapper;

import com.tf56.cps.api.qto.ImageAttachmentQTO;
import com.tf56.cps.domain.ImageAttachmentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *created by yeliming on 2021/2/27 17:19 <br/>
 */
public interface ImageAttachmentMapper {

    int insert(ImageAttachmentDO record);

    int batchInsert(@Param("list") List<ImageAttachmentDO> imageAttachmentDOs);

    List<ImageAttachmentDO> queryImageAttachmentList(ImageAttachmentQTO imageAttachmentQTO);

    int delete(ImageAttachmentDO deleteDO);
}