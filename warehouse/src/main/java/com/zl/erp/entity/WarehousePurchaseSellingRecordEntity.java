package com.zl.erp.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description: 进售货记录
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@Entity
@Table(name = "warehouse_purchase_selling_record")
public class WarehousePurchaseSellingRecordEntity {

    /**
     * 库存编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 物料类型编号
     */
    @Column(name = "product_kind_id")
    private Integer productKindId;

    /**
     * 物料类型
     */
    @Transient
    private String productKindName;

    /**
     * 物料类型
     */
    @Transient
    private String consumerName;

    /**
     * 数量
     */
    @Column(name = "stock_num")
    private String stockNum;

    /**
     * 仓管类型 0：退还厂方、1：进货、2：售货、3：退货
     */
    @Column(name = "manage_type")
    private String manageType;

    /**
     * 物料进价
     */
    @Column(name = "purchase_price")
    private String purchasePrice;

    /**
     * 物料售价
     */
    @Column(name = "selling_price")
    private String sellingPrice;

    /**
     * 客户编号
     */
    @Column(name = "consumer_id")
    private Integer consumerId;

    /**
     * 创建时间
     */
    @Column(name = "create_time", updatable = false)
    private String createTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;


}
