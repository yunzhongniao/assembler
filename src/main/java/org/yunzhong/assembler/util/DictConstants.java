package org.yunzhong.assembler.util;

/**
 * @author yunzhong
 *
 */
public class DictConstants {

    public static String DICT_CATEGORY_PRE = "DICT_CATEGORY_PRE";

    public static String generateKey(String category) {
        return String.format(DICT_CATEGORY_PRE + "_%s", category);
    }
}
