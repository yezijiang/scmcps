package com.tf56.cps.biz.impl;

import com.tf56.core.exception.BizException;
import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;
import com.tf56.cps.biz.ImageAttachmentBizService;
import com.tf56.cps.common.response.ResponseCode;
import com.tf56.cps.domain.ImageAttachmentDO;
import com.tf56.cps.helper.UrlHelper;
import com.tf56.cps.mapper.ImageAttachmentMapper;
import com.tf56.oms.action.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * created by yeliming on 2021/3/1 10:26 <br/>
 */
@Slf4j
@Service
public class ImageAttachmentBizServiceImpl implements ImageAttachmentBizService {

    @Resource
    private ImageAttachmentMapper imageAttachmentMapper;

    @Override
    public int batchInsert(List<ImageAttachmentDTO> imageAttachmentDTOs) {

        List<ImageAttachmentDO> imageAttachmentDOs = new ArrayList<>();
        for (ImageAttachmentDTO imageAttachmentDTO : imageAttachmentDTOs) {
            ImageAttachmentDO imageAttachmentDO = new ImageAttachmentDO();
            BeanUtils.copyProperties(imageAttachmentDTO, imageAttachmentDO);
            String imageUrl = imageAttachmentDO.getImageUrl();
            imageAttachmentDO.setImageUrl(UrlHelper.deleteDoggy(imageUrl));
            imageAttachmentDOs.add(imageAttachmentDO);
        }

        log.info("图片附件批量新增，入参:{}", JsonUtil.toJson(imageAttachmentDOs));
        int n = this.imageAttachmentMapper.batchInsert(imageAttachmentDOs);
        log.info("图片附件批量新增，应答n={}", n);

        if (n != imageAttachmentDOs.size()) {
            log.warn("图片附件批量新增异常");
            throw new BizException(ResponseCode.CPS_INSERT_ERROR, "图片附件批量新增异常");
        }

        return n;
    }

    @Override
    public List<ImageAttachmentDO> queryImageAttachmentList(ImageAttachmentQTO imageAttachmentQTO) {
        log.info("图片附件查询，入参:{}", JsonUtil.toJson(imageAttachmentQTO));
        List<ImageAttachmentDO> imageAttachmentDOs = this.imageAttachmentMapper.queryImageAttachmentList(imageAttachmentQTO);
        log.info("图片附件查询，应答size={}", imageAttachmentDOs.size());
        return imageAttachmentDOs;
    }

    @Override
    public int delete(List<Long> imageAttachmentIdList, Integer modifyOperatorId, String modifyOperator) {
        if (CollectionUtils.isEmpty(imageAttachmentIdList)) {
            return 0;
        }
        int count = 0;
        for (Long imageAttachmentId : imageAttachmentIdList) {
            ImageAttachmentDO deleteDO = new ImageAttachmentDO();
            deleteDO.setImageAttachmentId(imageAttachmentId);
            deleteDO.setModifyOperatorId(modifyOperatorId);
            deleteDO.setModifyOperator(modifyOperator);
            log.info("删除图片附件，入参:{}", JsonUtil.toJson(deleteDO));
            int n = this.imageAttachmentMapper.delete(deleteDO);
            log.info("删除图片附件，应答n={}", n);
            if (n != 1) {
                log.warn("删除图片附件异常");
                throw new BizException(ResponseCode.CPS_DELETE_ERROR, "删除图片附件异常");
            }
            count += n;
        }
        return count;
    }
}
