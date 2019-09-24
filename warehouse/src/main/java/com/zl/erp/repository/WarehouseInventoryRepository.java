package com.zl.erp.repository;

import com.zl.erp.entity.WarehouseInventoryEntity;

/**
 * @Description: 仓库存储
 * @Author: zhutao
 * @Date: 2019/9/24
 */
public interface WarehouseInventoryRepository extends BaseRepository<WarehouseInventoryEntity, Integer> {

    /**
     * 获取库存详情信息
     *
     * @param stockId 库存ID
     * @return 库存详情
     */
    WarehouseInventoryEntity getByStockId(Integer stockId);
}
