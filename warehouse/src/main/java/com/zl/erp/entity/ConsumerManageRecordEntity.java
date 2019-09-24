package com.zl.erp.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description: 客户管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Data
@Entity
@Table(name = "consumer_manage_record")
public class ConsumerManageRecordEntity {

    /**
     * 客户编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    @Excel(name = "客户编号", width = 10)
    private Integer consumerId;

    /**
     * 客户名称
     */
    @Column(name = "consumer_name")
    @Excel(name = "客户名称", width = 15)
    private String consumerName;

    /**
     * 客户类型 0：厂方、1：客户
     */
    @Column(name = "consumer_type")
    @Excel(name = "客户类型", width = 10)
    private String consumerType;

    /**
     * 联系方式
     */
    @Column(name = "contact_phone")
    @Excel(name = "联系方式", width = 15)
    private String contactPhone;

    /**
     * 联系地址
     */
    @Column(name = "contact_addr")
    @Excel(name = "联系地址", width = 20)
    private String contactAddr;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Excel(name = "创建时间", width = 15)
    private String createTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    @Excel(name = "备注", width = 15)
    private String remark;

}
