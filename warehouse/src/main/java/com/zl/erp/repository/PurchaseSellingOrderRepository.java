package com.zl.erp.repository;

import com.zl.erp.entity.PurchaseSellingOrderRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 订单管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public interface PurchaseSellingOrderRepository extends JpaRepository<PurchaseSellingOrderRecordEntity,Integer> {
}
