package org.yunzhong.assembler.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

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
        // TODO Auto-generated method stub

    }

    private void registerDictAssemblers(ConfigurableListableBeanFactory beanFactory) {
        // TODO Auto-generated method stub

    }

    private void registerCategories(ConfigurableListableBeanFactory beanFactory) {
        // TODO Auto-generated method stub

    }

}
