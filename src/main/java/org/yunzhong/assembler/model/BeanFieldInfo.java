package org.yunzhong.assembler.model;

import java.lang.reflect.Field;

import lombok.Builder;
import lombok.Data;

/**
 * @author yunzhong
 *
 */
@Data
@Builder
public class BeanFieldInfo {
    private String fieldName;
    private String idFieldName;
    private Field field;
    private Field idField;
    private String category;
}
