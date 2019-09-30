package com.zl.erp.repository;

import com.zl.erp.entity.WarehousePurchaseSellingRecordEntity;

import java.util.List;

/**
 * @Description: 仓库操作记录
 * @Author: zhutao
 * @Date: 2019/9/24
 */
public interface WarehousePurchaseSellingRepository extends BaseRepository<WarehousePurchaseSellingRecordEntity, Integer> {

    /**
     * 查询该用户下的进售货信息
     *
     * @param consumerId 用户ID
     * @return 信息
     */
    List<WarehousePurchaseSellingRecordEntity> queryAllByConsumerId(Integer consumerId);
}
