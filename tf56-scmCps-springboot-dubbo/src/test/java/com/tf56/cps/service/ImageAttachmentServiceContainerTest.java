package com.tf56.cps.service;

import com.tf56.core.BizReturn;
import com.tf56.core.page.PageResult;
import com.tf56.cps.api.ImageAttachmentService;
import com.tf56.cps.api.dto.ImageAttachmentDTO;
import com.tf56.cps.api.qto.ImageAttachmentQTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author : matthew
 * @description :
 * @date : 2021/3/5 10:42 上午
 **/
public class ImageAttachmentServiceContainerTest extends BaseTest {
    @Autowired
    private ImageAttachmentService queryImageAttachments;

    @Test
    public void testQueryImageAttachments(){
        ImageAttachmentQTO qto = new ImageAttachmentQTO();
        qto.setSupplierCode("0000TR");
        qto.setImageSource("会员");
        qto.setImageTypeList(Arrays.asList(new String[]{"营业执照","道路经营许可证","法人身份证正面","法人身份证反面"}));
        qto.setPageNo(0);
        BizReturn<PageResult<ImageAttachmentDTO>> pageResultBizReturn = queryImageAttachments.queryImageAttachments(qto);
        Assert.assertNotNull(pageResultBizReturn);
    }

}
