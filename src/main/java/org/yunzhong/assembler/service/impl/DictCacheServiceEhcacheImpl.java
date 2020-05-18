package org.yunzhong.assembler.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ehcache.core.Ehcache;
import org.yunzhong.assembler.dao.DictDBMapper;
import org.yunzhong.assembler.model.Dict;
import org.yunzhong.assembler.service.DictCacheService;
import org.yunzhong.assembler.util.DictConstants;

/**
 * @author yunzhong
 *
 */
public class DictCacheServiceEhcacheImpl implements DictCacheService {

    private Ehcache<String, List<Dict>> ehcache;

    private DictDBMapper dictDBMapper;

    @Override
    public Map<String, Dict> getByCategory(String category) {
        List<Dict> dicts = ehcache.get(DictConstants.generateKey(category));
        if (dicts == null) {
            dicts = dictDBMapper.getByCategory(category);
            ehcache.put(DictConstants.generateKey(category), dicts);
        }
        return dicts.stream().collect(Collectors.toMap(Dict::getKey, dict -> dict));
    }

    @Override
    public String getCnName(String category, String key) {
        Map<String, Dict> dicts = getByCategory(category);
        if (dicts != null && dicts.containsKey(key)) {
            return dicts.get(category).getCnName();
        }
        return null;
    }

    @Override
    public String getEnName(String category, String key) {
        Map<String, Dict> dicts = getByCategory(category);
        if (dicts != null && dicts.containsKey(key)) {
            return dicts.get(category).getEnName();
        }
        return null;
    }

    @Override
    public Dict getDict(String category, String key) {
        Map<String, Dict> dicts = getByCategory(category);
        if (dicts != null) {
            return dicts.get(category);
        }
        return null;
    }

}
