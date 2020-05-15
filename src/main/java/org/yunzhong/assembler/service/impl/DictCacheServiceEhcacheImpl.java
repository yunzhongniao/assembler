package org.yunzhong.assembler.service.impl;

import java.util.List;
import java.util.Map;

import org.ehcache.core.Ehcache;
import org.yunzhong.assembler.model.Dict;
import org.yunzhong.assembler.service.DictCacheService;

public class DictCacheServiceEhcacheImpl implements DictCacheService{
    
    private Ehcache<String, List<Dict>> ehcache;
    
    @Override
    public Map<String, Dict> getByCategory(String category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getCnName(String category, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getEnName(String category, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Dict getDict(String category, String key) {
        // TODO Auto-generated method stub
        return null;
    }

}
