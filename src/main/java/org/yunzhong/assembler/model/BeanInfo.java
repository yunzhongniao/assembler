package org.yunzhong.assembler.model;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

/**
 * @author yunzhong
 *
 */
@Data
@Builder
public class BeanInfo {
    private String beanName;
    private Class<?> beanClass;
    private boolean auto;
    private Map<String, BeanFieldInfo> fieldInfos;
}
