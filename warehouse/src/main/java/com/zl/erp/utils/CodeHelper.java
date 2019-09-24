package com.zl.erp.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 验证工具类
 * @Author: zhutao
 * @Date: 2019/9/19
 */
public class CodeHelper {
    private CodeHelper() {
    }

    public static boolean isNull(Object param) {
        return null == param;
    }

    public static boolean isNotNull(Object param) {
        return null != param;
    }

    public static boolean isNotNullOrEmpty(List<? extends Object> list) {
        return CodeHelper.isNotNull(list) && !list.isEmpty();
    }

    public static boolean isNotNullOrEmpty(Map<? extends Object, ? extends Object> map) {
        return CodeHelper.isNotNull(map) && !map.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String str) {
        return CodeHelper.isNotNull(str) && !str.isEmpty();
    }

    public static boolean isNullOrEmpty(String str) {
        return CodeHelper.isNull(str) || str.isEmpty();
    }

    public static boolean isNull(Integer params) {
        return null == params;
    }

    public static boolean isNotNull(Integer params) {
        return null != params;
    }

    public static boolean isNotNull(Long params) {
        return null != params;
    }

    public static boolean isNotNullOrEmpty(Object[] params) {
        return CodeHelper.isNotNull(params) && params.length > 0;
    }

    public static boolean isNullOrEmpty(Collection<?> list) {
        return CodeHelper.isNull(list) || list.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<? extends Object, ? extends Object> map) {
        return CodeHelper.isNull(map) || map.isEmpty();
    }
}
