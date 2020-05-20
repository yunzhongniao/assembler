package org.yunzhong.assembler.service;

import java.util.List;

import org.yunzhong.assembler.model.Dict;

/**
 * @author yunzhong
 *
 */
public interface DictDBService {

    List<Dict> getByCategory(String category);
}
