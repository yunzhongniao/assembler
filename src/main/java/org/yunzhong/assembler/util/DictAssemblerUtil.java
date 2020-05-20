package org.yunzhong.assembler.util;

import java.util.HashMap;
import java.util.Map;

import org.yunzhong.assembler.model.BeanInfo;
import org.yunzhong.assembler.service.BeanAssemblerService;

/**
 * @author yunzhong
 *
 */
public class DictAssemblerUtil {

    public static final Map<String, BeanInfo> beanCache = new HashMap<>();
    public static final Map<String, BeanAssemblerService> beanAssemblerCache = new HashMap<>();
}
