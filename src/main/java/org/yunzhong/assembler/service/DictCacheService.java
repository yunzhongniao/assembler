package org.yunzhong.assembler.service;

import java.util.Map;

import org.yunzhong.assembler.model.Dict;

/**
 * @author yunzhong
 *
 */
public interface DictCacheService {

    Map<String, Dict> getByCategory(String category);

    String getCnName(String category, String key);

    String getEnName(String category, String key);

    Dict getDict(String category, String key);
}
