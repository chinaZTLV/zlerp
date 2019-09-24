package com.zl.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 分页响应
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDataPage extends ResponseData {

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
    protected int totalCount = 0;
    /**
     * 总页数
     */
    protected int totalPage = 0;

}
