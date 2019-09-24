package com.zl.erp.common.db;

/**
 * @Description: sql
 * @Author: zhutao
 * @Date: 2019/9/19
 */
public enum FilterKeyword {

    /**
     * 等于（=）
     */
    EQ,
    /**
     * 小于（<）
     */
    LT,
    /**
     * 小于且等于（<=）
     */
    LTE,
    /**
     * 大于（>）
     */
    GT,
    /**
     * 大于且等于（>=）
     */
    GTE,

    /**
     * 模糊查询(like)
     */
    LK,
    /**
     * 模糊查询(not like)
     */
    NLK,

    /**
     * IS NOT NULL
     */
    ISNN,

    /**
     * IS NULL
     */
    ISN,

    /**
     * IN
     */
    IN,

    /**
     * 不等于（=）
     */
    NEQ,

    /**
     * between and
     */
    BETWEEN,

    /**
     * date
     */
    DATEBETWEEN,

    /**
     * asc
     */
    ASC,

    /**
     * desc
     */
    DESC
}
