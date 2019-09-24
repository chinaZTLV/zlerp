package com.zl.erp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description: 仓库库存管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@Entity
@Table(name = "warehouse_inventory_manage")
public class WarehouseInventoryManageEntity {

    /**
     * 库存编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Integer stockId;

    /**
     * 物料类型编号
     */
    @Column(name = "product_kind_id")
    private String productKindId;

    /**
     * 库存数量
     */
    @Column(name = "stock_num")
    private Integer stockNum;

    /**
     * 总价
     */
    @Column(name = "total_amount")
    private String totalAmount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Integer updateTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}
