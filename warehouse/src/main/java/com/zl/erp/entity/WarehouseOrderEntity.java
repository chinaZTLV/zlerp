package com.zl.erp.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Description: 订单视图
 * @Author: zhutao
 * @Date: 2019/9/26
 */
@Data
@Entity
@Table(name = "v_warehouse_order")
public class WarehouseOrderEntity {
    /**
     * 订单ID
     */
    @Id
    @Excel(name = "订单编号", width = 15)
    private Integer orderId;

    /**
     * 已下单、已发货、已付款
     */
    @Excel(name = "交易状态", width = 15)
    private String tradeType;

    /**
     * 0：退还厂方、1：进货、2：售货、3：退货
     */
    @Excel(name = "交易类型", width = 15)
    private String manageType;

    /**
     * 物料类型ID
     */
    private Integer productKindId;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称", width = 15)
    private String productKindName;

    /**
     * 数量
     */
    @Excel(name = "数量", width = 15)
    private String stockNum;

    /**
     * 单位
     */
    @Excel(name = "单位", width = 15)
    private Integer unit;

    /**
     * 单价
     */
    @Excel(name = "单价", width = 15)
    private String unitPrice;

    /**
     * 折扣
     */
    @Excel(name = "折扣", width = 15)
    private String discount;

    /**
     * 折扣金额
     */
    @Excel(name = "折扣金额", width = 15)
    private String discountAmount;

    /**
     * 物料进价
     */
    @Excel(name = "物料进价", width = 15)
    private String purchasePrice;

    /**
     * 总金额
     */
    @Excel(name = "总金额", width = 15)
    private String totalAmount;

    /**
     * 利润
     */
    @Excel(name = "利润", width = 15)
    private String netReceipt;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称", width = 15)
    private String consumerName;

    /**
     * 客户ID
     */
    private Integer consumerId;

    /**
     * 订单创建时间
     */
    @Excel(name = "订单创建时间", width = 15)
    private String createTime;

    /**
     * 开始时间
     */
    @Transient
    private String createStartTime;

    /**
     * 结束时间
     */
    @Transient
    private String createEndTime;

}
