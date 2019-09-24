package com.zl.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 消息头
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    public String resultCode;

    /**
     * 返回描述
     */
    public String resultDesc;


}
