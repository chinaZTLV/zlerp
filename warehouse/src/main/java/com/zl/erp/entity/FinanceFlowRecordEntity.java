package com.zl.erp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description: 财务流水
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@Entity
@Table(name = "finance_flow_record")
public class FinanceFlowRecordEntity {

    /**
     * 流水ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flow_id")
    private Integer flowId;

    /**
     * 流水号
     */
    @Column(name = "flow_number")
    private String flowNumber;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 产品类型
     */
    @Column(name = "product_kind_id")
    private Integer productKindId;

    /**
     * 流水金额
     */
    @Column(name = "flow_amount")
    private String flowAmount;

    /**
     * 流水类型 0：退还厂方、1：进货、2：售货、3：退货
     */
    @Column(name = "flow_type")
    private Integer flowType;

    /**
     * 流水时间
     */
    @Column(name = "flow_time")
    private String flowTime;

    /**
     * 付款方式 银行卡转账、现金、支付宝支付、微信支付
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 客户名称
     */
    @Column(name = "consumer_id")
    private Integer consumerId;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;


}
