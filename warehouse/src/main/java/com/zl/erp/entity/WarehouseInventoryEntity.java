package com.zl.erp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "v_warehouse_inventory")
public class WarehouseInventoryEntity {

    /**
     * 库存编号
     */
    @Id
    private Integer stockId;

    /**
     * 物料类型ID
     */
    private String productKindId;

    /**
     * 库存
     */
    private Integer stockNum;

    /**
     * 总金额
     */
    private String totalAmount;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 厂商ID
     */
    private int consumerId;

    /**
     * 厂商名称
     */
    private String consumerName;

    /**
     * 厂商类型
     */
    private Integer consumerType;

    /**
     * 物料名称
     */
    private String productKindName;

    /**
     * 物料类型 0：块、1：个、2：米、3：平方
     */
    private Integer unit;

    /**
     * 联系方式
     */
    private String contactPhone;

    /**
     * 联系地址
     */
    private String contactAddr;
}
