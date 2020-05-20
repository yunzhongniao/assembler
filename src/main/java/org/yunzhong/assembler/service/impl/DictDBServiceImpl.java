package org.yunzhong.assembler.service.impl;

import java.util.List;

import org.yunzhong.assembler.dao.DictDBMapper;
import org.yunzhong.assembler.model.Dict;
import org.yunzhong.assembler.service.DictDBService;

/**
 * @author yunzhong
 *
 */
public class DictDBServiceImpl implements DictDBService {

    private DictDBMapper dictMapper;
    
    @Override
    public List<Dict> getByCategory(String category) {
        return dictMapper.getByCategory(category);
    }

}
