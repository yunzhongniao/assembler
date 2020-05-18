package org.yunzhong.assembler.util;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.yunzhong.assembler.anno.DictClassAnno;
import org.yunzhong.assembler.anno.DictFieldAnno;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DictPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        registerCategories(beanFactory);
        registerDictAssemblers(beanFactory);
        registerDictBeanInfos(beanFactory);
    }

    private void registerDictBeanInfos(ConfigurableListableBeanFactory beanFactory) {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(DictClassAnno.class);
        if (beansWithAnnotation != null && !beansWithAnnotation.isEmpty()) {
            for (Iterator<Entry<String, Object>> iterator = beansWithAnnotation.entrySet().iterator(); iterator
                    .hasNext();) {
                Entry<String, Object> entry = iterator.next();
                String beanName = entry.getKey();
                Object bean = entry.getValue();
                Class<? extends Object> beanClass = bean.getClass();
                Field[] declaredFields = beanClass.getDeclaredFields();
                while (beanClass != null && Object.class != beanClass) {
                    if (declaredFields != null) {
                        for (int i = 0; i < declaredFields.length; i++) {
                            Field field = declaredFields[i];
                            DictFieldAnno annotation = field.getAnnotation(DictFieldAnno.class);
                            if (annotation != null) {
                                
                            }
                        }
                    }
                }
            }
        }
    }

    private void registerDictAssemblers(ConfigurableListableBeanFactory beanFactory) {
        // TODO Auto-generated method stub

    }

    private void registerCategories(ConfigurableListableBeanFactory beanFactory) {
        // TODO Auto-generated method stub

    }

}
