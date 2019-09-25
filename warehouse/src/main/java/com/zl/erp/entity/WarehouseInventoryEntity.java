package com.zl.erp.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 库存管理视图
 * @Author: zhutao
 * @Date: 2019/9/25
 */
@Data
@Entity
@Table(name = "v_warehouse_inventory")
public class WarehouseInventoryEntity {

    /**
     * 库存编号
     */
    @Id
    @Excel(name = "库存编号", width = 15)
    private Integer stockId;

    /**
     * 物料类型ID
     */
    private String productKindId;

    /**
     * 物料名称
     */
    @Excel(name = "物料名称", width = 15)
    private String productKindName;

    /**
     * 库存
     */
    @Excel(name = "库存", width = 15)
    private Integer stockNum;

    /**
     * 物料单位 0：块、1：个、2：米、3：平方
     */
    @Excel(name = "物料单位", width = 15)
    private String unit;

    /**
     * 总金额
     */
    @Excel(name = "总金额（元）", width = 15)
    private String totalAmount;

    /**
     * 厂商名称
     */
    @Excel(name = "厂商名称", width = 15)
    private String consumerName;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 15)
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
     * 联系方式
     */
    private String contactPhone;

    /**
     * 联系地址
     */
    private String contactAddr;
}
