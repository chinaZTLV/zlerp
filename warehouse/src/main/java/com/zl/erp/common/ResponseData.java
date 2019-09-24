package com.zl.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 响应结构
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private String resultCode;

    /**
     * 返回描述
     */
    private String resultDesc;

    /**
     * 消息报文内容
     */
    private T data;
}
