package com.zl.erp.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 售货记录
 * @Author: zhutao
 * @Date: 2019/9/30
 */
@Data
@Entity
@Table(name = "v_purchase_selling_record")
public class PurchaseSellingRecordEntity {
    @Id
    @Excel(name = "进售货记录编号", width = 15)
    private Integer recordId;
    @Excel(name = "订单编号", width = 15)
    private Integer orderId;
    @Excel(name = "数量", width = 15)
    private String stockNum;
    private Integer productKindId;
    @Excel(name = "仓管类型", width = 15)
    private String manageType;
    @Excel(name = "进价", width = 15)
    private String purchasePrice;
    @Excel(name = "售价", width = 15)
    private String sellingPrice;
    private Integer consumerId;
    @Excel(name = "客户名称", width = 15)
    private String consumerName;
    @Excel(name = "物料名称", width = 15)
    private String productKindName;
    @Excel(name = "创建时间", width = 15)
    private String createTime;
}
