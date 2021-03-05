package com.tf56.cps.util;

import com.tf56.cps.api.enm.FileTypeEnum;
import com.tf56.cps.domain.ImageAttachmentDO;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by yeliming on 2021/3/1 16:48 <br/>
 */
public class ImageUtil {

    public static void reflect(Object instance, List<ImageAttachmentDO> imageAttachmentDOs) {
        Map<String/*imageType*/, String/*imageUrl*/> map = imageAttachmentDOs.stream().collect(Collectors.toMap(ImageAttachmentDO::getImageType, ImageAttachmentDO::getImageUrl, (e1, e2) -> e1));
        Class<?> clazz = instance.getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                if (pd.getName().equals("class")) {
                    continue;
                }

                String propertyName = pd.getName();

                //imageType转fieldName
                String byFieldName = FileTypeEnum.getByFieldName(propertyName);
                if (StringUtils.isNotBlank(byFieldName)) {
                    Method writeMethod = pd.getWriteMethod();
                    if (writeMethod != null) {
                        String imageUrl = map.get(byFieldName);
                        writeMethod.invoke(instance,  FastDfsCpsGroup2Util.addSign4Url(imageUrl));
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
