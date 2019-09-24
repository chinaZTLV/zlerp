package com.zl.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @Description: 请求体
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestData<T> {
    /**
     * 请求头
     */
    private Header header;

    /**
     * 请求体
     */
    @Valid
    private T body;

}
