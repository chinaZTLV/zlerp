package com.zl.erp.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description: 物料类型管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@Entity
@Table(name = "material_kind_manage")
public class MaterialKindManageEntity {

    /**
     * 产品编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel(name = "产品编号", width = 10)
    @Column(name = "product_kind_id")
    private Integer productKindId;

    /**
     * 物料名称
     */
    @Column(name = "product_kind_name")
    @Excel(name = "物料名称", width = 15)
    private String productKindName;

    /**
     * 厂方名称
     */
    @Transient
    @Excel(name = "厂方名称", width = 15)
    private String consumerName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Excel(name = "创建时间", width = 15)
    private String createTime;

    /**
     * 物料进价
     */
    @Column(name = "purchase_price")
    @Excel(name = "物料进价", width = 15)
    private String purchasePrice;

    /**
     * 物料售价
     */
    @Column(name = "selling_price")
    @Excel(name = "物料售价", width = 15)
    private String sellingPrice;

    /**
     * 售货单位 0：块、1：个、2：米、3：平方
     */
    @Column(name = "unit")
    @Excel(name = "售货单位", width = 15)
    private String unit;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @Excel(name = "修改时间", width = 15)
    private String updateTime;

    /**
     * 客户编号
     */
    @Column(name = "consumer_id")
    private Integer consumerId;

    /**
     * 备注
     */
    @Column(name = "remark")
    @Excel(name = "备注", width = 15)
    private String remark;


    /**
     * 客户IDs
     */
    @Transient
    private String consumerIds;

}
