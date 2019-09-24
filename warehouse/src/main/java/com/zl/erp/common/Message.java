package com.zl.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 响应体
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {

    /**
     * 响应头
     */
    public Header header;

    /**
     * 响应结果集
     */
    public T body;
}
