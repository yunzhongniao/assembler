package org.yunzhong.assembler.model;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeanInfo {
    private String beanName;
    private Class<?> beanClass;
    private Map<String, BeanFieldInfo> fieldInfos;
}
