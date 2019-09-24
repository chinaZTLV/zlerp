package com.zl.erp.repository;

import com.zl.erp.entity.FinanceFlowRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 财务流水管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public interface FinanceFlowRecordRepository extends JpaRepository<FinanceFlowRecordEntity, Integer> {
}
