package com.tf56.cps.manager.impl;

import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;
import com.tf56.cps.biz.ImageAttachmentBizService;
import com.tf56.cps.domain.ImageAttachmentDO;
import com.tf56.cps.manager.ImageAttachmentManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * created by yeliming on 2021/3/1 15:49 <br/>
 */
@Slf4j
@Service
public class ImageAttachmentManagerImpl implements ImageAttachmentManager {

    @Resource
    private ImageAttachmentBizService imageAttachmentBizService;

    @Override
    public List<ImageAttachmentDTO> queryImageAttachmentList(ImageAttachmentQTO imageAttachmentQTO) {

        List<ImageAttachmentDO> imageAttachmentDOs = this.imageAttachmentBizService.queryImageAttachmentList(imageAttachmentQTO);

        List<ImageAttachmentDTO> imageAttachmentDTOs = new ArrayList<>();
        for (ImageAttachmentDO imageAttachmentDO : imageAttachmentDOs) {
            ImageAttachmentDTO imageAttachmentDTO = new ImageAttachmentDTO();
            BeanUtils.copyProperties(imageAttachmentDO, imageAttachmentDTO);
            imageAttachmentDTOs.add(imageAttachmentDTO);
        }

        return imageAttachmentDTOs;
    }
}
