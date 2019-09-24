package com.zl.erp.common.db;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: 排序
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Data
@AllArgsConstructor
public class FilterOrder {

    /**
     * fieldName
     */
    private String name;

    /**
     * 关键词
     */
    private FilterKeyword filterKeyword;
}
