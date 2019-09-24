package com.zl.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 分页
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDataPage<T> extends RequestData<T> {

    /**
     * 索引值
     */
    protected int pageIndex = 1;
    /**
     * 每页条数
     */
    protected int pageSize = 10;
    /**
     * 总条数
     */
    protected int totalCount;
    /**
     * 总页数
     */
    protected int totalPage;
}
