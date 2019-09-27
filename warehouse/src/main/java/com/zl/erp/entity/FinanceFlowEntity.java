package com.zl.erp.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 财务
 * @Author: zhutao
 * @Date: 2019/9/27
 */
@Data
@Entity
@Table(name = "v_finance_flow")
public class FinanceFlowEntity {

    /**
     * 流水ID
     */
    @Id
    @Excel(name = "流水ID", width = 15)
    private Integer flowId;

    /**
     * 订单ID
     */
    @Excel(name = "订单ID", width = 15)
    private Integer orderId;

    /**
     * 流水号
     */
    @Excel(name = "流水号", width = 15)
    private String flowNumber;

    /**
     * 产品类型
     */
    private Integer productKindId;

    /**
     * 流水金额
     */
    @Excel(name = "流水金额", width = 15)
    private String flowAmount;

    /**
     * 流水类型
     */
    @Excel(name = "流水类型", width = 15)
    private String flowType;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称", width = 15)
    private String consumerName;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称", width = 15)
    private String productKindName;

    /**
     * 流水时间
     */
    @Excel(name = "流水时间", width = 15)
    private String flowTime;

    /**
     * 客户ID
     */
    private Integer consumerId;

}
