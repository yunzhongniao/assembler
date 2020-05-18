package org.yunzhong.assembler.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yunzhong.assembler.model.Dict;

/**
 * @author yunzhong
 *
 */
public interface DictDBMapper {

    List<Dict> getByCategory(@Param("category") String category);

}
