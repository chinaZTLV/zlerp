package com.zl.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 分页响应信息
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageMessage extends Message {

    /**
     * 索引值
     */
    public int pageIndex = 1;
    /**
     * 每页条数
     */
    public int pageSize = 10;
    /**
     * 总条数
     */
    public int totalCount = 0;
    /**
     * 总页数
     */
    public int totalPage = 0;
}
