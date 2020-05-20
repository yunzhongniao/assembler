package org.yunzhong.assembler.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.yunzhong.assembler.anno.DictAssemblerAnno;
import org.yunzhong.assembler.anno.DictClassAnno;
import org.yunzhong.assembler.anno.DictClassAutoAnno;
import org.yunzhong.assembler.anno.DictFieldAnno;
import org.yunzhong.assembler.model.BeanFieldInfo;
import org.yunzhong.assembler.model.BeanInfo;
import org.yunzhong.assembler.service.BeanAssemblerService;

import lombok.extern.log4j.Log4j2;

/**
 * @author yunzhong
 *
 */
@Component
@Log4j2
public class DictPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            registerCategories();
        } catch (IOException e) {
            log.error("Failed to register categories.", e);
        }
        registerDictAssemblers(beanFactory);
        registerDictBeanInfos(beanFactory);
        registerDictAutoBeanInfos(beanFactory);
    }

    private void registerDictAutoBeanInfos(ConfigurableListableBeanFactory beanFactory) {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(DictClassAutoAnno.class);
        if (beansWithAnnotation != null && !beansWithAnnotation.isEmpty()) {
            for (Iterator<Entry<String, Object>> iterator = beansWithAnnotation.entrySet().iterator(); iterator.hasNext();) {
                Entry<String, Object> entry = iterator.next();
                Object bean = entry.getValue();
                Class<? extends Object> beanClass = bean.getClass();
                Field[] declaredFields = beanClass.getDeclaredFields();
                BeanInfo beanInfo = BeanInfo.builder().beanClass(beanClass).beanName(beanClass.getSimpleName()).fieldInfos(new HashMap<>()).auto(true).build();
                if (declaredFields != null) {
                    for (int i = 0; i < declaredFields.length; i++) {
                        Field field = declaredFields[i];
                        String fieldName = field.getName();
                        if (!fieldName.endsWith("Name")) {
                            continue;
                        }
                        Field idField = ReflectionUtils.findField(beanClass, fieldName.substring(0, fieldName.length() - 4));
                        if (idField == null) {
                            continue;
                        }
                        if (DictFactory.category_map.containsKey(idField.getName())) {
                            ReflectionUtils.makeAccessible(field);
                            ReflectionUtils.makeAccessible(idField);
                            BeanFieldInfo fieldInfo = BeanFieldInfo.builder().field(field).idField(idField).fieldName(field.getName())
                                    .idFieldName(idField.getName()).category(DictFactory.category_map.get(idField.getName())).build();
                            beanInfo.getFieldInfos().put(field.getName(), fieldInfo);
                        }
                    }
                }
                DictAssemblerUtil.beanCache.put(beanClass.getSimpleName(), beanInfo);
            }
        }
    }

    private void registerDictBeanInfos(ConfigurableListableBeanFactory beanFactory) {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(DictClassAnno.class);
        if (beansWithAnnotation != null && !beansWithAnnotation.isEmpty()) {
            for (Iterator<Entry<String, Object>> iterator = beansWithAnnotation.entrySet().iterator(); iterator.hasNext();) {
                Entry<String, Object> entry = iterator.next();
                Object bean = entry.getValue();
                Class<? extends Object> beanClass = bean.getClass();
                Field[] declaredFields = beanClass.getDeclaredFields();
                BeanInfo beanInfo = BeanInfo.builder().beanClass(beanClass).beanName(beanClass.getSimpleName()).fieldInfos(new HashMap<>()).auto(false).build();
                while (beanClass != null && Object.class != beanClass) {
                    if (declaredFields != null) {
                        for (int i = 0; i < declaredFields.length; i++) {
                            Field field = declaredFields[i];
                            DictFieldAnno annotation = field.getAnnotation(DictFieldAnno.class);
                            if (annotation != null) {
                                Field idField = ReflectionUtils.findField(beanClass, annotation.property());
                                ReflectionUtils.makeAccessible(field);
                                ReflectionUtils.makeAccessible(idField);
                                BeanFieldInfo fieldInfo = BeanFieldInfo.builder().field(field).idField(idField).fieldName(field.getName())
                                        .idFieldName(idField.getName()).category(annotation.category()).build();
                                beanInfo.getFieldInfos().put(field.getName(), fieldInfo);
                            }
                        }
                    }
                }
                DictAssemblerUtil.beanCache.put(beanClass.getSimpleName(), beanInfo);
            }
        }
    }

    private void registerDictAssemblers(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanAssemblerService> beansOfType = beanFactory.getBeansOfType(BeanAssemblerService.class);
        if (beansOfType == null || beansOfType.isEmpty()) {
            log.info("There is no assembler service found.");
            return;
        }
        for (Iterator<Entry<String, BeanAssemblerService>> iterator = beansOfType.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, BeanAssemblerService> type = iterator.next();
            BeanAssemblerService value = type.getValue();
            DictAssemblerAnno annotation = value.getClass().getAnnotation(DictAssemblerAnno.class);
            if (annotation != null) {
                DictAssemblerUtil.beanAssemblerCache.put(annotation.value(), value);
            }
        }
    }

    private void registerCategories() throws IOException {
        DictFactory.loadCategoryResource();
    }

}
