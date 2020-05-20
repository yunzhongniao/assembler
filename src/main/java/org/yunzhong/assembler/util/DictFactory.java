package org.yunzhong.assembler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * @author yunzhong
 *
 */
public class DictFactory {
    public static final Logger LOGGER = LoggerFactory.getLogger(DictFactory.class);

    public static final Map<String, String> category_map = new HashMap<>();

    public static void loadCategoryResource() throws IOException {
        File file = ResourceUtils.getFile("classloader:categories.properties");
        Properties properties = new Properties();
        try (FileInputStream stream = new FileInputStream(file)) {
            properties.load(stream);
        }
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            DictFactory.category_map.put(String.valueOf(key), String.valueOf(properties.get(key)));
        }
    }
}
