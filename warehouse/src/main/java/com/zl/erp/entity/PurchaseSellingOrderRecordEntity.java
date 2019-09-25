package com.zl.erp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Description: 订单管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@Entity
@Table(name = "purchase_selling_order_record")
public class PurchaseSellingOrderRecordEntity {

    /**
     * 订单编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 交易状态 已下单、已发货、已付款
     */
    @Column(name = "trade_type")
    private String tradeType;

    /**
     * 交易类型 0：退还厂方、1：进货、2：售货、3：退货
     */
    @Column(name = "manage_type")
    private Integer manageType;

    /**
     * 物料类型编号
     */
    @Column(name = "product_kind_id")
    private Integer productKindId;

    /**
     * 物料数量
     */
    @Column(name = "stock_num")
    private String stockNum;

    /**
     * 单价
     */
    @Column(name = "unit_price")
    private String unitPrice;

    /**
     * 折扣
     */
    @Column(name = "discount")
    private String discount;

    /**
     * 订单折扣金额 单价*数量*折扣
     */
    @Column(name = "discount_amount")
    private String discountAmount;

    /**
     * 物料进价
     */
    @Column(name = "purchase_price")
    private String purchasePrice;

    /**
     * 订单总金额
     */
    @Column(name = "total_amount")
    private String totalAmount;

    /**
     * 订单利润
     */
    @Column(name = "net_receipt")
    private String netReceipt;

    /**
     * 客户编号
     */
    @Column(name = "consumer_id")
    private Integer consumerId;

    /**
     * 付款方式 银行卡转账、现金、支付宝支付、微信支付
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 交易时间
     */
    @Column(name = "trade_time")
    private String tradeTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;


}
