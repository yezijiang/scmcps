package com.tf56.cps.common.util;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DozerUtil {
    private static Mapper dozer = new DozerBeanMapper();

    public static void conversion(Object source, Object target) {
        dozer.map(source, target);
    }

    public static <T> T conversion(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        return dozer.map(source, targetClass);
    }


    public static <T> List<T> conversionList(List<?> source, Class<T> targetClass) {
        List<T> result = null;
        if (source != null) {
            result = new ArrayList(source.size());
            if (!source.isEmpty()) {
                for (Object obj : source) {
                    result.add(conversion(obj, targetClass));
                }
            }
        }
        return result;
    }


    private static <S> void recursionFields(S bean, Map<String, Object> fieldsMap, Object instance) {

        Class clazz;
        if (bean instanceof Class) {
            clazz = (Class) bean;
        } else {
            clazz = bean.getClass();
        }

        Class superClazz = clazz.getSuperclass();
        if (superClazz != null && !superClazz.equals(Class.class)) {
            recursionFields(superClazz, fieldsMap, instance);
        }

        for (Field field : clazz.getDeclaredFields()) {
            try {
                int mod = field.getModifiers();
                if (Modifier.isFinal(mod)) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method readMethod = pd.getReadMethod();
                Object value = readMethod.invoke(instance);
                fieldsMap.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，那么sourceBean中的值会覆盖tagetBean重点的值
     *
     * @param sourceBean 被提取的对象bean
     * @param targetBean 用于合并的对象bean
     * @return targetBean, 合并后的对象
     */
    public static <S, T> T combineObj(S sourceBean, T targetBean) {
        Map<String, Object> sourceFieldsMap = new HashMap<>();
        recursionFields(sourceBean, sourceFieldsMap, sourceBean);
        Map<String, Object> targetFieldsMap = new HashMap<>();
        recursionFields(targetBean, targetFieldsMap, targetBean);

        for (Map.Entry<String, Object> sourceEntry : sourceFieldsMap.entrySet()) {
            String key = sourceEntry.getKey();
            Object value = sourceEntry.getValue();
            if (targetFieldsMap.containsKey(key)) {
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(key, targetBean.getClass());
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(targetBean, value);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return targetBean;

    }


}
