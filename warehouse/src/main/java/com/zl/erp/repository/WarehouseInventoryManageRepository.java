package com.zl.erp.repository;

import com.zl.erp.entity.WarehouseInventoryManageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 仓库
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public interface WarehouseInventoryManageRepository extends BaseRepository<WarehouseInventoryManageEntity, Integer> {

    /**
     * 获取库存信息
     *
     * @param productKindId 物料类型
     * @return 库存
     */
    WarehouseInventoryManageEntity getByProductKindId(Integer productKindId);

    /**
     * 获取库存详情
     *
     * @param stockId 库存ID
     * @return 结果
     */
    WarehouseInventoryManageEntity getByStockId(Integer stockId);
}
