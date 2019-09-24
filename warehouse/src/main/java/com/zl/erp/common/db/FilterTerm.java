package com.zl.erp.common.db;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: 查询过滤项
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Data
public class FilterTerm {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数关系
     */
    private FilterRelation filterRelation = FilterRelation.AND;

    /**
     * 参数关键字
     */
    private FilterKeyword filterKeyword = FilterKeyword.EQ;

    public FilterTerm(String name) {
        this.name = name;
    }

    public FilterTerm(String name, FilterRelation relation) {
        this(name);
        this.filterRelation = relation;
    }

    public FilterTerm(String name, FilterKeyword keyword) {
        this(name);
        this.filterKeyword = keyword;
    }

    public FilterTerm(String name, FilterRelation relation, FilterKeyword keyword) {
        this(name, relation);
        this.filterKeyword = keyword;
    }
}
